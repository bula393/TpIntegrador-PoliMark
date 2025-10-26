package com.polimark.polimark.controler;

import com.web.cineapp.model.ButacaEstado;
import com.web.cineapp.service.ButacaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funciones")
@CrossOrigin(origins = "*")
public class ButacaController {

    private final ButacaService butacaService;

    public ButacaController(ButacaService butacaService) {
        this.butacaService = butacaService;
    }

    @GetMapping("/{funcionId}/butacas")
    public List<ButacaEstado> obtenerMapaButacas(@PathVariable int funcionId) {
        return butacaService.obtenerMapaButacas(funcionId);
    }
}

