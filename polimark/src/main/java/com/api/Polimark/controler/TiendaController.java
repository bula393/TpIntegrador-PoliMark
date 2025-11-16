package com.api.Polimark.controler;

import com.api.Polimark.service.ProductoService;
import com.api.Polimark.dto.ProductoVisible;
import com.api.Polimark.dto.RangoHorario;
import com.api.Polimark.modelo.CategoriaProducto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tienda")
@CrossOrigin(origins = "*")
public class TiendaController {

    private ProductoService productoService;

    @GetMapping("/categorias")
    public List<CategoriaProducto> categorias() {
        return List.of(CategoriaProducto.values());
    }

    @GetMapping("/productosVisibles")
    public ResponseEntity<List<ProductoVisible>> productosVisibles(@RequestParam CategoriaProducto filter) {
        try {
            List<ProductoVisible> productos = productoService.obtenerProductos(filter);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
