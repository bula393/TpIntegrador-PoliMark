package com.api.Polimark.controler;

import com.api.Polimark.dto.ArticuloPromocionRequest;
import com.api.Polimark.modelo.Articulo;
import com.api.Polimark.service.ArticuloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/articulos")
@CrossOrigin(origins = "*")
public class ArticuloController {

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    // Endpoint alternativo que recibe directamente la lista
    @PostMapping("/adquirirArticulos")
    public ResponseEntity<List<Articulo>> crearArticulosConPromocionesList(
            @RequestBody List<Map<Integer, Integer>> articulosPromociones,
            @RequestBody(required = true) int idUsuario) {
        try {
            List<Articulo> articulosCreados = articuloService.crearArticulosConPromociones(articulosPromociones,idUsuario);
            return ResponseEntity.ok(articulosCreados);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}