package com.api.Polimark.service;
import com.api.Polimark.dto.*;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private ProductoRepository productoRepository;
    private PromocionRepository promocionRepository;

    public ProductoService(ProductoRepository productoRepository, PromocionRepository promocionRepository) {
        this.productoRepository = productoRepository;
        this.promocionRepository = promocionRepository;
    }

    public List<ProductoVisible> obtenerProductos(CategoriaProducto filter) {
        List<Producto> productos;

        if (filter == CategoriaProducto.COMBO) {
            // Para combos, obtenemos las promociones de tipo COMBO
            return obtenerCombos();
        } else {
            // Para otras categorías, obtenemos productos normales
            productos = productoRepository.findByCategoria(filter.toString());
        }

        return convertirAProductosVisibles(productos);
    }

    private List<ProductoVisible> obtenerCombos() {
        List<Promocion> promocionesCombo = promocionRepository.findByTipo(TipoPromo.COMBO.toString());
        List<ProductoVisible> combos = new ArrayList<>();

        for (Promocion promocion : promocionesCombo) {
            ProductoVisible comboVisible = convertirPromocionAComboVisible(promocion);
            combos.add(comboVisible);
        }

        return combos;
    }

    private ProductoVisible convertirPromocionAComboVisible(Promocion promocion) {
        // Extraer información del combo de las características
        String[] partes = promocion.getCaracteristicas().split("-");
        String nombreCombo = partes.length > 0 ? partes[0] : "Combo";
        Integer precioCombo = partes.length > 1 ? Integer.parseInt(partes[1]) : 0;

        return new ProductoVisible(
                promocion.getIdPromocion(), // Usamos el ID de la promoción como ID temporal
                nombreCombo,
                promocion.getCaracteristicas(), // Descripción con detalles del combo
                "COMBO",
                precioCombo,
                1, // Cantidad por defecto
                999 // Stock alto para combos
        );
    }

    private List<ProductoVisible> convertirAProductosVisibles(List<Producto> productos) {
        List<ProductoVisible> productosVisibles = new ArrayList<>();

        for (Producto producto : productos) {
            ProductoVisible productoVisible = new ProductoVisible(
                    producto.getArticuloIdArticulo(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getCategoria(),
                    producto.getArticulo().getPrecio(),
                    1, // Cantidad por defecto
                    producto.getStock()
            );
            productosVisibles.add(productoVisible);
        }

        return productosVisibles;
    }
}