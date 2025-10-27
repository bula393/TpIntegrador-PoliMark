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
    @PostMapping("/crear-con-promociones-list")
    public ResponseEntity<List<Articulo>> crearArticulosConPromocionesList(
            @RequestBody List<Map<Integer, Integer>> articulosPromociones) {
        try {
            List<Articulo> articulosCreados = articuloService.crearArticulosConPromociones(articulosPromociones);
            return ResponseEntity.ok(articulosCreados);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}