package com.api.Polimark.service;

import com.api.Polimark.dto.ArticuloPromocionRequest;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ArticuloService {
    private final ArticuloRepository articuloRepository;
    private final PromocionRepository promocionRepository;
    private final PromocionHasArticuloRepository promocionHasArticuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository,
                           PromocionRepository promocionRepository,
                           PromocionHasArticuloRepository promocionHasArticuloRepository) {
        this.articuloRepository = articuloRepository;
        this.promocionRepository = promocionRepository;
        this.promocionHasArticuloRepository = promocionHasArticuloRepository;
    }

    @Transactional
    public List<Articulo> crearArticulosConPromociones(List<Map<Integer, Integer>> articulosPromociones) {
        List<Articulo> articulosCreados = new ArrayList<>();

        for (Map<Integer, Integer> articuloPromo : articulosPromociones) {
            // Crear nuevo artículo (ID autoincremental)
            Articulo nuevoArticulo = new Articulo();
            nuevoArticulo.setPrecio(Entrada.getPrecioBasa()); // Ejemplo

            Articulo articuloGuardado = articuloRepository.save(nuevoArticulo);
            articulosCreados.add(articuloGuardado);

            // Procesar las promociones asociadas
            for (Map.Entry<Integer, Integer> entry : articuloPromo.entrySet()) {
                Integer idPromocion = entry.getKey();
                Integer cantidad = entry.getValue();

                // Si el idPromocion no es null, crear la relación
                if (idPromocion != null) {
                    // Verificar si la promoción existe
                    Promocion promocion = promocionRepository.findById(idPromocion)
                            .orElseThrow(() -> new RuntimeException("Promoción no encontrada con ID: " + idPromocion));

                    // Crear la relación PromocionHasArticulo
                    PromocionHasArticulo promocionHasArticulo = new PromocionHasArticulo();
                    promocionHasArticulo.setPromocion(promocion);
                    promocionHasArticulo.setArticulo(articuloGuardado);
                    // Si tienes campo para cantidad en la tabla de unión, setearlo aquí
                    // promocionHasArticulo.setCantidad(cantidad);

                    promocionHasArticuloRepository.save(promocionHasArticulo);
                }
            }
        }

        return articulosCreados;
    }

    // Método alternativo que recibe el DTO
    @Transactional
    public List<Articulo> crearArticulosConPromociones(ArticuloPromocionRequest request) {
        return crearArticulosConPromociones(request.getArticulosPromociones());
    }
}