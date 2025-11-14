package com.api.Polimark.service;

import com.api.Polimark.dto.*;
import com.api.Polimark.dto.SolicitudEntradas;
import com.api.Polimark.dto.ArticuloPromocionRequest;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraService {
    private final EntradaService entradaService;
    private final EntradaRepository entradaRepository;
    private final FuncionRepository funcionRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final CompraRepository compraRepository;
    private final ArticuloRepository articuloRepository;
    private final ButacaRepository butacaRepository;
    private final CompraHasPromocionRepository compraHasPromocionRepository;
    private final ProductoHasCompraRepository productoHasCompraRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArticuloService articuloService;


    public CompraService(EntradaService entradaService, EntradaRepository entradaRepository, FuncionRepository funcionRepository,
                         MetodoPagoRepository metodoPagoRepository, CompraRepository compraRepository, ArticuloRepository articuloRepository, ButacaRepository butacaRepository, CompraHasPromocionRepository compraHasPromocionRepository, ProductoHasCompraRepository productoHasCompraRepository, ProductoRepository productoRepository, UsuarioRepository usuarioRepository, ArticuloService articuloService)
    {
        this.entradaService = entradaService;
        this.entradaRepository = entradaRepository;
        this.funcionRepository = funcionRepository;
        this.metodoPagoRepository = metodoPagoRepository;
        this.compraRepository = compraRepository;
        this.articuloRepository = articuloRepository;
        this.butacaRepository = butacaRepository;
        this.compraHasPromocionRepository = compraHasPromocionRepository;
        this.productoHasCompraRepository = productoHasCompraRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
        this.articuloService = articuloService;
    }

    public Compra reservarCompra(int idFuncion, int idCompra, List<Integer> articulosId, List<Integer> butacasid)  {
        Funcion funcion = funcionRepository.findById(idFuncion)
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));

        // Validar que las listas tengan el mismo tamaño
        if (butacasid.size() != articulosId.size()) {
            throw new RuntimeException("La cantidad de butacas (" + butacasid.size() +
                    ") no coincide con la cantidad de artículos (" + articulosId.size() + ")");
        }

        // Validar que no exceda el límite de 6 butacas
        if (butacasid.size() > 6) {
            throw new RuntimeException("jaj");
        }

        // Si pasa todas las validaciones, procesar la reserva
        int contador = 0;
        Compra nuevaCompra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        for (Integer butacaId : butacasid) {
            Articulo articulo = articuloRepository.findById(articulosId.get(contador))
                    .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

            Butaca butaca = butacaRepository.findById(butacaId)
                    .orElseThrow(() -> new RuntimeException("Butaca no encontrada"));

            entradaService.reservarAsiento(nuevaCompra, articulo, funcion, butaca);
            contador++;
        }

        return nuevaCompra;
    }

    public ResumenCompra generarResumenCompra(int idCompra) {
        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        // Obtener entradas
        List<Entrada> entradas = entradaRepository.findByCompraIdCompra(idCompra);
        List<EntradaVisible> entradasVisibles = entradas.stream()
                .map(entrada -> new EntradaVisible(
                        entrada.getIdArticulo(),
                        entrada.getArticulo().getPrecio(),
                        entrada.getButaca(),
                        entrada.getFuncion()
                ))
                .collect(Collectors.toList());

        // Obtener productos
        List<ProductoHasCompra> productosCompra = productoHasCompraRepository.findByCompraIdCompra(idCompra);
        List<ProductoVisible> productosVisibles = productosCompra.stream()
                .map(pc -> {
                    Producto producto = pc.getProducto();
                    return new ProductoVisible(
                            producto.getArticuloIdArticulo(),
                            producto.getNombre(),
                            producto.getDescripcion(),
                            producto.getCategoria(),
                            producto.getArticulo().getPrecio(),
                            pc.getCantidad(),
                            producto.getStock()
                    );
                })
                .collect(Collectors.toList());

        // Obtener información de la función (usamos la primera entrada como referencia)
        Funcion funcionCompra = entradas.get(0).getFuncion();
        SalaVisible salaVisible = new SalaVisible(
                funcionCompra.getSala().getIdSala(),
                funcionCompra.getSala().getCapacidad(),
                funcionCompra.getSala().getTipo(),
                funcionCompra.getSala().getLugar().getNombre()
        );
        FuncionVisible funcionVisible = new FuncionVisible(
                funcionCompra.getIdFuncion(),
                funcionCompra.getHorario(),
                salaVisible,
                funcionCompra.getPelicula()
        );
        // Obtener butacas desde las entradas
        List<ButacaVisible> butacasVisibles = entradas.stream()
                .map(entrada -> {
                    Butaca butaca = entrada.getButaca();
                    return new ButacaVisible(
                            butaca.getIdButaca(),
                            butaca.getColumna(),
                            butaca.getFila(),
                            new SalaVisible(
                                    funcionCompra.getSala().getIdSala(),
                                    funcionCompra.getSala().getCapacidad(),
                                    funcionCompra.getSala().getTipo(),
                                    funcionCompra.getSala().getLugar().getNombre()
                            )
                    );
                })
                .collect(Collectors.toList());
        // Calcular total con promociones aplicadas
        Integer total = calcularTotal(compra);

        return new ResumenCompra(butacasVisibles, entradasVisibles, productosVisibles, funcionVisible, total);
    }

    @Transactional
    public Compra reservarCompraConArticulos(SolicitudEntradas solicitudEntradas) {
        // 1. Validar datos básicos
        validarSolicitud(solicitudEntradas);

        // 2. Crear compra temporal
        Compra compra = crearCompraInicial(solicitudEntradas.getIdUsuario());

        // 3. Crear artículos con promociones
        List<Articulo> articulosCreados = articuloService.crearArticulosConPromociones(
                solicitudEntradas.getArticulosPromociones(),
                solicitudEntradas.getIdUsuario()
        );

        // 4. Extraer información para la reserva
        Integer funcionId = obtenerFuncionId(solicitudEntradas);
        List<Integer> articulosIds = articulosCreados.stream()
                .map(Articulo::getIdArticulo)
                .collect(Collectors.toList());

        List<Integer> butacasIds = obtenerButacasIds(solicitudEntradas);

        // 5. Llamar al método original de reservarCompra con toda la lógica de negocio
        Compra compraReservada = reservarCompra(funcionId, compra.getIdCompra(), articulosIds, butacasIds);

        // 6. Calcular y actualizar el total usando el método existente
        Integer total = calcularTotal(compraReservada);

        return compraRepository.save(compraReservada);
    }

    @Transactional
    public Compra reservarCompra(int idFuncion, int idCompra, List<Integer> articulosId, List<Integer> butacasid) {
        // VALIDACIÓN 1: Verificar que las listas tengan el mismo tamaño
        if (butacasid.size() != articulosId.size()) {
            throw new RuntimeException("La cantidad de butacas (" + butacasid.size() +
                    ") no coincide con la cantidad de artículos (" + articulosId.size() + ")");
        }

        // VALIDACIÓN 2: Límite máximo de 6 butacas por compra
        if (butacasid.size() > 6) {
            throw new RuntimeException("No se pueden reservar más de 6 butacas por compra");
        }

        // VALIDACIÓN 3: Verificar que la función existe
        Funcion funcion = funcionRepository.findById(idFuncion)
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));

        // VALIDACIÓN 4: Verificar que la compra existe
        Compra compra = compraRepository.findById(idCompra)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        // VALIDACIÓN 5: Verificar que las butacas están disponibles
        validarDisponibilidadButacas(butacasid, funcion);

        // PROCESAR RESERVA
        int contador = 0;
        for (Integer butacaId : butacasid) {
            Articulo articulo = articuloRepository.findById(articulosId.get(contador))
                    .orElseThrow(() -> new RuntimeException("Artículo no encontrado con ID: " + articulosId.get(contador)));

            Butaca butaca = butacaRepository.findById(butacaId)
                    .orElseThrow(() -> new RuntimeException("Butaca no encontrada con ID: " + butacaId));

            // Reservar asiento
            entradaService.reservarAsiento(compra, articulo, funcion, butaca);
            contador++;
        }

        return compra;
    }


    private void validarSolicitud(SolicitudEntradas solicitudEntradas) {
        if (solicitudEntradas.getIdUsuario() == null) {
            throw new RuntimeException("ID de usuario es requerido");
        }

        if (solicitudEntradas.getArticulosPromociones() == null ||
                solicitudEntradas.getArticulosPromociones().isEmpty()) {
            throw new RuntimeException("La lista de artículos no puede estar vacía");
        }

        // Validar que la cantidad de butacas no exceda la cantidad de entradas
        int cantidadButacas = solicitudEntradas.getArticulosPromociones().stream()
                .filter(ap -> ap.getButacasIds() != null)
                .mapToInt(ap -> ap.getButacasIds().size())
                .sum();

        int cantidadEntradas = solicitudEntradas.getArticulosPromociones().stream()
                .filter(ap -> "ENTRADA".equals(ap.getTipo()) || "ENTRADA_AUTO".equals(ap.getTipo()))
                .mapToInt(ArticuloPromocionRequest::getCantidad)
                .sum();

        if (cantidadButacas > cantidadEntradas) {
            throw new RuntimeException("La cantidad de butacas (" + cantidadButacas +
                    ") excede la cantidad de entradas (" + cantidadEntradas + ")");
        }
    }

    private Compra crearCompraInicial(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Compra compra = new Compra();
        compra.setPagado(false);
        compra.setUsuario(usuario);

        return compraRepository.save(compra);
    }

    private Integer obtenerFuncionId(SolicitudEntradas solicitudEntradas) {
        return solicitudEntradas.getArticulosPromociones().stream()
                .filter(ap -> ap.getFuncionId() != null)
                .findFirst()
                .map(ap -> ap.getFuncionId())
                .orElseThrow(() -> new RuntimeException("No se encontró función ID en la solicitud"));
    }

    private List<Integer> obtenerButacasIds(SolicitudEntradas solicitudEntradas) {
        return solicitudEntradas.getArticulosPromociones().stream()
                .filter(ap -> ap.getButacasIds() != null)
                .flatMap(ap -> ap.getButacasIds().stream())
                .collect(Collectors.toList());
    }

    private void validarDisponibilidadButacas(List<Integer> butacasIds, Funcion funcion) {
        for (Integer butacaId : butacasIds) {
            Butaca butaca = butacaRepository.findById(butacaId)
                    .orElseThrow(() -> new RuntimeException("Butaca no encontrada: " + butacaId));

            // Verificar que la butaca pertenece a una sala de la función
            if (!butaca.getSala().getIdSala().equals(funcion.getSala().getIdSala())) {
                throw new RuntimeException("La butaca " + butacaId + " no pertenece a la sala de la función");
            }
        }
    }


public Integer calcularTotal(Compra compra) {
    Integer totalBase = 0;

    // Sumar precio de entradas
    List<Entrada> entradas = entradaRepository.findByCompraIdCompra(compra.getIdCompra());
    for (Entrada entrada : entradas) {
        // Usar el precio del artículo asociado a la entrada
        totalBase += entrada.getArticulo().getPrecio();
    }

    // Sumar precio de productos
    List<ProductoHasCompra> productos = productoHasCompraRepository.findByCompraIdCompra(compra.getIdCompra());
    for (ProductoHasCompra productoCompra : productos) {
        Producto producto = productoCompra.getProducto();
        totalBase += producto.getArticulo().getPrecio() * productoCompra.getCantidad();
    }

    // Aplicar promociones
    List<CompraHasPromocion> promocionesAplicadas = compraHasPromocionRepository.findByCompra_idCompra(compra.getIdCompra());
    Integer totalConPromociones = aplicarPromociones(totalBase, promocionesAplicadas, entradas, productos);

    // Aplicar descuento del método de pago
    Integer totalFinal = aplicarDescuentoMetodoPago(totalConPromociones, compra.getMetodoPago());

    return totalFinal;
}

private Integer aplicarPromociones(Integer totalBase, List<CompraHasPromocion> promociones,
                                   List<Entrada> entradas, List<ProductoHasCompra> productos) {
    Integer total = totalBase;

    for (CompraHasPromocion compraPromo : promociones) {
        Promocion promocion = compraPromo.getPromociones();
        Integer cantidadAplicada = compraPromo.getCantidad();

        switch (promocion.getTipo()) {
            case DESCUENTO:
                total = aplicarDescuentoPorcentual(total, promocion.getCaracteristicas());
                break;

            case DOSXUNO:
                total = aplicarDosPorUno(total, promocion.getCaracteristicas(), entradas, productos);
                break;

            case COMBO:
                total = aplicarCombo(total, promocion.getCaracteristicas(), productos);
                break;
        }
    }

    return total;
}

private Integer aplicarDescuentoPorcentual(Integer total, String caracteristicas) {
    try {
        // Extraer porcentaje de descuento (ejemplo: "20-combos-martes")
        String[] partes = caracteristicas.split("-");
        if (partes.length > 0) {
            String porcentajeStr = partes[0].replaceAll("[^0-9]", "");
            if (!porcentajeStr.isEmpty()) {
                Integer porcentaje = Integer.parseInt(porcentajeStr);
                return total * (100 - porcentaje) / 100;
            }
        }
    } catch (Exception e) {
        // Log error pero continuar
        System.err.println("Error aplicando descuento: " + e.getMessage());
    }
    return total;
}

private Integer aplicarDosPorUno(Integer total, String caracteristicas,
                                 List<Entrada> entradas, List<ProductoHasCompra> productos) {
    try {
        if (caracteristicas.contains("entrada")) {
            // Aplicar 2x1 en entradas
            int cantidadEntradas = entradas.size();
            int entradasPagas = (cantidadEntradas + 1) / 2; // Redondeo hacia arriba
            int precioEntrada = Entrada.getPrecioBasa(); // Usar precio base de entrada

            // Recalcular total restando entradas no pagas
            int descuentoEntradas = (cantidadEntradas - entradasPagas) * precioEntrada;
            return total - descuentoEntradas;
        }
        else if (caracteristicas.contains("bebida") || caracteristicas.contains("producto")) {
            // Aplicar 2x1 en productos específicos
            for (ProductoHasCompra productoCompra : productos) {
                if (productoCompra.getCantidad() >= 2) {
                    // Por cada 2 productos, pagar 1
                    int productosGratis = productoCompra.getCantidad() / 2;
                    int descuento = productosGratis * productoCompra.getProducto().getArticulo().getPrecio();
                    return total - descuento;
                }
            }
        }
    } catch (Exception e) {
        System.err.println("Error aplicando 2x1: " + e.getMessage());
    }
    return total;
}

private Integer aplicarCombo(Integer total, String caracteristicas, List<ProductoHasCompra> productos) {
    try {
        // Ejemplo: "Pochoclos+Bebida-3000" -> precio fijo 3000 para el combo
        String[] partes = caracteristicas.split("-");
        if (partes.length > 1) {
            String precioComboStr = partes[partes.length - 1].replaceAll("[^0-9]", "");
            if (!precioComboStr.isEmpty()) {
                Integer precioCombo = Integer.parseInt(precioComboStr);

                // Verificar si los productos del combo están en la compra
                String productosCombo = partes[0].toLowerCase();
                boolean tieneCombo = productos.stream()
                        .anyMatch(pc -> productosCombo.contains(pc.getProducto().getNombre().toLowerCase()));

                if (tieneCombo) {
                    // Calcular precio original de los productos del combo
                    int precioOriginalCombo = productos.stream()
                            .filter(pc -> productosCombo.contains(pc.getProducto().getNombre().toLowerCase()))
                            .mapToInt(pc -> pc.getProducto().getArticulo().getPrecio() * pc.getCantidad())
                            .sum();

                    // Aplicar descuento del combo
                    return total - precioOriginalCombo + precioCombo;
                }
            }
        }
    } catch (Exception e) {
        System.err.println("Error aplicando combo: " + e.getMessage());
    }
    return total;
}

private Integer aplicarDescuentoMetodoPago(Integer total, MetodoPago metodoPago) {
    if (metodoPago != null && metodoPago.getDescuento() != null) {
        return total * (100 - metodoPago.getDescuento()) / 100;
    }
    return total;
}
//public ResumenCompra pagarCompra(int idCompra,int idMetodoPago) {
//    Optional<Compra> compraOptional = compraRepository.findById(idCompra);
//    Compra compra = compraOptional.get();
//    MetodoPago metodoPago = metodoPagoRepository.findById(idMetodoPago)
//            .orElseThrow(() -> new RuntimeException("Metodo de Pago no encontrada"));
//    compra.setMetodoPago(metodoPago);
//    compra.setPagado(true);
//    compraRepository.save(compra);
//    return generarResumenCompra(idCompra);
//}
}
