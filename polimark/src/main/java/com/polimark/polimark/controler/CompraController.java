package com.polimark.polimark.controler;

import com.polimark.polimark.modelo.Funcion;
import com.polimark.polimark.service.FuncionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/funciones")
@CrossOrigin(origins = "*")
public class CompraController {

    private final CompraService compraService;

    public FuncionController(CompraService compraService) {
        this.funcionService = funcionService;
    }

    @GetMapping("/reservar")
    public List<Funcion> buscarFunciones(
            @RequestParam(required = true) List<Butaca> butacas;
            @RequestParam(required = true) int idUsuario,
            @RequestParam(required = true) int idMetodoPago
            @RequestParam(required = true) int idFuncion;
    ) {
        compraService.reservarCompra(idFuncion,idUsuario,idMetodoPago,butacas);
    }
}
