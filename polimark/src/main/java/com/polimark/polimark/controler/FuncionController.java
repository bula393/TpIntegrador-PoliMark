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
public class FuncionController {

    private final FuncionService funcionService;

    public FuncionController(FuncionService funcionService) {
        this.funcionService = funcionService;
    }

    @GetMapping("/buscar")
    public List<Funcion> buscarFunciones(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,

            @RequestParam(required = false) String sucursalNombre,
            @RequestParam(required = false) String peliculaNombre
    ) {
        return funcionService.buscarFunciones(fecha, sucursalNombre, peliculaNombre);
    }
}
