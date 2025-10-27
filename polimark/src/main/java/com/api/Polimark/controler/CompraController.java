package com.api.Polimark.controler;

import com.api.Polimark.modelo.*;
import com.api.Polimark.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/compra")
@CrossOrigin(origins = "*")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public ResponseEntity<Compra> reserva(@RequestParam(required = true) List<Butaca> butacas,
                                     @RequestParam(required = true) int idUsuario,
                                     @RequestParam(required = true) int idFuncion,
                                    @RequestParam(required = true) List<Articulo> articulos) {
        try {
            return ResponseEntity.ok(compraService.reservarCompra(idFuncion,idUsuario,articulos,butacas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
