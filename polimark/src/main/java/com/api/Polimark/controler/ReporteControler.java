package com.api.Polimark.controler;

import com.api.Polimark.dto.PeliculaHorario;
import com.api.Polimark.modelo.Funcion;
import com.api.Polimark.service.PeliculaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/reporte")
@CrossOrigin(origins = "*")
public class ReporteControler {

    private final PeliculaService peliculaService;

    public ReporteControler(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping("/taquilla/top")
    public PeliculaHorario peliculaMasTaquillera(
            @RequestParam(required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,

            @RequestParam(required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta
    ) {
        return peliculaService.peliculaMasTaquillera(desde, hasta);
    }
}
