package com.api.Polimark.service;

import com.api.Polimark.dto.*;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public CompraService(EntradaService entradaService, EntradaRepository entradaRepository, FuncionRepository funcionRepository,
                         MetodoPagoRepository metodoPagoRepository, CompraRepository compraRepository, ArticuloRepository articuloRepository, ButacaRepository butacaRepository, CompraHasPromocionRepository compraHasPromocionRepository, ProductoHasCompraRepository productoHasCompraRepository, ProductoRepository productoRepository)
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
        }
    public Compra reservarCompra(int idFuncion, int idCompra,List<Integer> articulosId, List<Integer> butacasid) {
        Funcion funcion = funcionRepository.findById(idFuncion)
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));
        if (butacasid.size() == articulosId.size()) {}
            if(butacasid.size() > 6){
                int contador = 0;
                Compra nuevaCompra = compraRepository.findById(idCompra)
                        .orElseThrow(() -> new RuntimeException("compra no encontrada"));;
                for(Integer butacaId : butacasid){
                        Articulo articulo = articuloRepository.findById(articulosId.get(contador))
                                .orElseThrow(() -> new RuntimeException("articulo no encontrado"));

                        Butaca butaca = butacaRepository.findById(butacaId)
                                .orElseThrow(() -> new RuntimeException("butaca no encontrado"));
                        entradaService.reservarAsiento(nuevaCompra,articulo,funcion,butaca);
                        contador++;
                    }
                return nuevaCompra;
            }
            else{
                throw new excedeLimiteExeption();
            }

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
     //   Integer totalFinal = aplicarDescuentoMetodoPago(totalConPromociones, compra.getMetodoPago());

        return totalConPromociones;
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

//    private Integer aplicarDescuentoMetodoPago(Integer total, MetodoPago metodoPago) {
//        if (metodoPago != null && metodoPago.getDescuentoPorcentaje() != null) {
//            return total * (100 - metodoPago.getDescuentoPorcentaje()) / 100;
//        }
//        return total;
//    }
    public ResumenCompra pagarCompra(int idCompra,int idMetodoPago) {
        Optional<Compra> compraOptional = compraRepository.findById(idCompra);
        Compra compra = compraOptional.get();
        MetodoPago metodoPago = metodoPagoRepository.findById(idMetodoPago)
        .orElseThrow(() -> new RuntimeException("Metodo de Pago no encontrada"));
        compra.setMetodoPago(metodoPago);
        compra.setPagado(true);
        compraRepository.save(compra);
        return generarResumenCompra(idCompra);
    }
}
