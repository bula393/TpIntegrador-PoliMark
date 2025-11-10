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
    private final CompraRepository compraRepository;
    private final PromocionRepository promocionRepository;
    private final CompraHasPromocionRepository compraHasPromocionRepository;
    private final UsuarioRepository usuarioRepository;

    public ArticuloService(ArticuloRepository articuloRepository, CompraRepository compraRepository,
                           PromocionRepository promocionRepository,
                           CompraHasPromocionRepository compraHasPromocionRepository, UsuarioRepository usuarioRepository) {
        this.articuloRepository = articuloRepository;
        this.compraRepository = compraRepository;
        this.promocionRepository = promocionRepository;
        this.compraHasPromocionRepository = compraHasPromocionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public List<Articulo> crearArticulosConPromociones(List<Map<Integer, Integer>> articulosPromociones, int idUsuario) {
        List<Articulo> articulosCreados = new ArrayList<>();
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Compra compraGuardada = compraRepository.save(new Compra(false, null, usuario)); // CAPTURAR LA COMPRA GUARDADA

        for (Map<Integer, Integer> articuloPromo : articulosPromociones) {
            Articulo nuevoArticulo = new Articulo();
            nuevoArticulo.setPrecio(Entrada.getPrecioBasa());

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
                            .orElseThrow(() -> new RuntimeException("Promoción no encontrada"));

                    // Usar compraGuardada en lugar de nuevaCompra
                    CompraHasPromocion compraHasPromocion = new CompraHasPromocion(compraGuardada, promocion, cantidad);
                    compraHasPromocionRepository.save(compraHasPromocion); // esta línea ya debería funcionar
                }
            }
        }
        return articulosCreados;
    }


}