package com.api.Polimark.service;

import com.api.Polimark.dto.*;
import com.api.Polimark.dto.SolicitudEntradas;
import com.api.Polimark.dto.ArticuloPromocionRequest;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    private final PromocionRepository promocionRepository;
    private final EntradaAutoRepository entradaAutoRepository;

    public CompraService(EntradaService entradaService, EntradaRepository entradaRepository, FuncionRepository funcionRepository,
                         MetodoPagoRepository metodoPagoRepository, CompraRepository compraRepository, ArticuloRepository articuloRepository, ButacaRepository butacaRepository, CompraHasPromocionRepository compraHasPromocionRepository, ProductoHasCompraRepository productoHasCompraRepository, ProductoRepository productoRepository, UsuarioRepository usuarioRepository, PromocionRepository promocionRepository, EntradaAutoRepository entradaAutoRepository)
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
        this.promocionRepository = promocionRepository;
        this.entradaAutoRepository = entradaAutoRepository;
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
                    .orElseThrow(() -> new RuntimeException("Artículo no encontrado con ID"));

            Butaca butaca = butacaRepository.findById(butacaId)
                    .orElseThrow(() -> new RuntimeException("Butaca no encontrada con ID" ));

            // Reservar asiento
            entradaService.reservarAsiento(compra, articulo, funcion, butaca);
            contador++;
        }

        return compra;
    }

    private List<Integer> obtenerButacasIds(SolicitudEntradas solicitudEntradas) {
        List<Integer> todasLasButacas = new ArrayList<>();

        if (solicitudEntradas.getArticulosPromociones() != null) {
            for (ArticuloPromocionRequest articulo : solicitudEntradas.getArticulosPromociones()) {
                if (articulo.getButacasIds() != null && !articulo.getButacasIds().isEmpty()) {
                    todasLasButacas.addAll(articulo.getButacasIds());
                }
            }
        }

        return todasLasButacas;
    }

    private void validarSolicitud(SolicitudEntradas solicitudEntradas) {
        if (solicitudEntradas.getArticulosPromociones() == null ||
                solicitudEntradas.getArticulosPromociones().isEmpty()) {
            throw new RuntimeException("La lista de artículos no puede estar vacía");
        }

        int cantidadButacas = calcularCantidadButacas(solicitudEntradas);
        int cantidadEntradas = calcularCantidadEntradas(solicitudEntradas);

        if (cantidadButacas > cantidadEntradas) {
            throw new RuntimeException("La cantidad de butacas (" + cantidadButacas +
                    ") excede la cantidad de entradas (" + cantidadEntradas + ")");
        }
    }

    private int calcularCantidadButacas(SolicitudEntradas solicitudEntradas) {
        int totalButacas = 0;

        for (ArticuloPromocionRequest articulo : solicitudEntradas.getArticulosPromociones()) {
            if (articulo.getButacasIds() != null) {
                totalButacas += articulo.getButacasIds().size();
            }
        }

        return totalButacas;
    }

    private int calcularCantidadEntradas(SolicitudEntradas solicitudEntradas) {
        int totalEntradas = 0;

        for (ArticuloPromocionRequest articulo : solicitudEntradas.getArticulosPromociones()) {
            if ("ENTRADA".equals(articulo.getTipo()) || "ENTRADA_AUTO".equals(articulo.getTipo())) {
                totalEntradas += articulo.getCantidad();
            }
        }

        return totalEntradas;
    }

    private Integer obtenerFuncionId(SolicitudEntradas solicitudEntradas) {
        for (ArticuloPromocionRequest articulo : solicitudEntradas.getArticulosPromociones()) {
            if (articulo.getFuncionId() != null) {
                return articulo.getFuncionId();
            }
        }
        throw new RuntimeException("No se encontró función ID en la solicitud");
    }

    @Transactional
    public Compra reservarCompraConArticulos(SolicitudEntradas solicitudEntradas) {
        // 1. Validar datos básicos
        validarSolicitud(solicitudEntradas);

        // 2. Crear compra temporal
        Compra compra = crearCompraInicial(solicitudEntradas.getIdUsuario());

        // 3. Crear artículos con promociones (integrado aquí)
        List<Articulo> articulosCreados = crearArticulosYPromociones(solicitudEntradas.getArticulosPromociones(), compra);

        // 4. Extraer información para la reserva
        Integer funcionId = obtenerFuncionId(solicitudEntradas);
        List<Integer> articulosIds = new ArrayList<>();
        List<Integer> butacasIds = new ArrayList<>();

        // 5. Procesar tanto ENTRADA como ENTRADA_AUTO
        int articuloIndex = 0;
        for (ArticuloPromocionRequest articuloRequest : solicitudEntradas.getArticulosPromociones()) {
            if ("ENTRADA".equals(articuloRequest.getTipo()) || "ENTRADA_AUTO".equals(articuloRequest.getTipo())) {
                // Agregar artículo
                articulosIds.add(articulosCreados.get(articuloIndex).getIdArticulo());

                // Agregar butacas (tanto para entrada normal como auto)
                if (articuloRequest.getButacasIds() != null) {
                    butacasIds.addAll(articuloRequest.getButacasIds());
                }
            }
            articuloIndex++;
        }

        // 6. Llamar al método original de reservarCompra
        Compra compraReservada = reservarCompra(funcionId, compra.getIdCompra(), articulosIds, butacasIds);

        return compraRepository.save(compraReservada);
    }

    private List<Articulo> crearArticulosYPromociones(List<ArticuloPromocionRequest> articulosPromociones, Compra compra) {
        List<Articulo> articulosCreados = new ArrayList<>();

        for (ArticuloPromocionRequest articuloRequest : articulosPromociones) {
            // Crear artículos según la cantidad solicitada
            for (int i = 0; i < articuloRequest.getCantidad(); i++) {
                Articulo nuevoArticulo = new Articulo();

                // Usar el precio del request o el precio base si no está definido
                if (articuloRequest.getPrecio() != null) {
                    nuevoArticulo.setPrecio(articuloRequest.getPrecio());
                } else {
                    nuevoArticulo.setPrecio(Entrada.getPrecioBasa());
                }

                Articulo articuloGuardado = articuloRepository.save(nuevoArticulo);
                articulosCreados.add(articuloGuardado);

                // Crear relaciones adicionales según el tipo
                if ("ENTRADA_AUTO".equals(articuloRequest.getTipo())) {
                    crearEntradaAuto(articuloRequest, articuloGuardado, compra);
                } else if ("PRODUCTO".equals(articuloRequest.getTipo())) {
                    crearProductoCompra(articuloRequest, articuloGuardado, compra);
                }
            }

            // Procesar promociones aplicadas
            if (articuloRequest.getPromocionesAplicadas() != null) {
                for (Integer idPromocion : articuloRequest.getPromocionesAplicadas()) {
                    Promocion promocion = promocionRepository.findById(idPromocion)
                            .orElseThrow(() -> new RuntimeException("Promoción no encontrada con ID: " + idPromocion));

                    CompraHasPromocion compraHasPromocion = new CompraHasPromocion(
                            compra,
                            promocion,
                            articuloRequest.getCantidad()
                    );
                    compraHasPromocionRepository.save(compraHasPromocion);
                }
            }
        }

        return articulosCreados;
    }

    private void crearEntradaAuto(ArticuloPromocionRequest articuloRequest, Articulo articulo, Compra compra) {
        if (articuloRequest.getPatente() == null || articuloRequest.getCantidadAuto() == null) {
            throw new RuntimeException("Patente y cantidadAuto son requeridos para ENTRADA_AUTO");
        }

        EntradaAuto entradaAuto = new EntradaAuto();
        entradaAuto.setEntradaArticuloIdArticulo(articulo.getIdArticulo());
        entradaAuto.setPatente(articuloRequest.getPatente());
        entradaAuto.setCantidadAuto(articuloRequest.getCantidadAuto());

        entradaAutoRepository.save(entradaAuto);
    }

    private void crearProductoCompra(ArticuloPromocionRequest articuloRequest, Articulo articulo, Compra compra) {
        if (articuloRequest.getProductoId() == null) {
            throw new RuntimeException("productoId es requerido para PRODUCTO");
        }

        Producto producto = productoRepository.findById(articuloRequest.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + articuloRequest.getProductoId()));

        if (articuloRequest.getCantidad() > producto.getStock()) {
            throw new  RuntimeException("stock insuficiente");
        }
        ProductoHasCompra productoHasCompra = new ProductoHasCompra();
        productoHasCompra.setCompra(compra);
        productoHasCompra.setProducto(producto);
        productoHasCompra.setCantidad(articuloRequest.getCantidad());

        productoHasCompraRepository.save(productoHasCompra);
    }


    private Compra crearCompraInicial(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Compra compra = new Compra();
        compra.setPagado(false);
        compra.setUsuario(usuario);

        return compraRepository.save(compra);
    }

    private void validarDisponibilidadButacas(List<Integer> butacasIds, Funcion funcion) {
        for (Integer butacaId : butacasIds) {
            Butaca butaca = butacaRepository.findById(butacaId)
                    .orElseThrow(() -> new RuntimeException("Butaca no encontrada: " + butacaId));

            // Verificar que la butaca pertenece a una sala de la función
            if (!(butaca.getSala().getIdSala() == funcion.getSala().getIdSala())) {
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
public ResumenCompra pagarCompra(PagoCompra pagoCompra) {
    Optional<Compra> compraOptional = compraRepository.findById(pagoCompra.getIdCompra());
    Compra compra = compraOptional.get();
    MetodoPago metodoPago = metodoPagoRepository.findById(pagoCompra.getIdMetodoPago())
            .orElseThrow(() -> new RuntimeException("Metodo de Pago no encontrada"));
    compra.setMetodoPago(metodoPago);
    compra.setPagado(true);
    compraRepository.save(compra);
    return generarResumenCompra(pagoCompra.getIdCompra());
}
}
