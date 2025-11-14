package com.api.Polimark.controler;

import com.api.Polimark.dto.ResumenCompra;
import com.api.Polimark.dto.SolicitudEntradas;
import com.api.Polimark.modelo.*;
import com.api.Polimark.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compra")
@CrossOrigin(origins = "*")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }


    @PostMapping("/reservarEntrada")
    public ResponseEntity<?> crearEntradasConPromocionesList(
            @RequestBody SolicitudEntradas solicitudEntradas) {
        try {
            // Delegar toda la lógica al servicio de compra
            Compra compraReservada = compraService.reservarCompraConArticulos(solicitudEntradas);
            return ResponseEntity.ok(compraReservada);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }



//    @PostMapping("/reservar")
//    public ResponseEntity<?> reserva(@RequestParam(required = true) int idMetodoPago,
//                                     @RequestParam(required = true) int idCompra) {
//        try {
//            return ResponseEntity.ok(compraService.pagarCompra(idCompra,idMetodoPago));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

//    @GetMapping("/resumenCompra/{id}")
//    public ResumenCompra getResumenCompra(@PathVariable Integer idCompra) {
//        return compraService.generarResumenCompra(idCompra);
//    }


}
