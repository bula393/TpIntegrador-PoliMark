package com.api.Polimark.controler;

import com.api.Polimark.modelo.Funcion;
import com.api.Polimark.modelo.Pelicula;
import com.api.Polimark.modelo.Rango;
import com.api.Polimark.service.FuncionService;
import com.api.Polimark.service.PeliculaService;
import com.api.Polimark.service.RangoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rango")
@CrossOrigin(origins = "*")
public class RangoControler {

    private final RangoService rangoService;

    public RangoControler(RangoService rangoService) {
        this.rangoService = rangoService;
    }

    @GetMapping("/rangosDisponibles")
    public List<Rango> rangosDisponibles(
    ) {
        return rangoService.rangos();
    }
}
