package com.polimark.Polimark.controler;

import com.polimark.Polimark.dto.ButacaEstado;
import com.polimark.Polimark.service.ButacaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/butaca")
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

