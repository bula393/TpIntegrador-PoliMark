package com.api.Polimark.controler;

import com.api.Polimark.dto.PeliculaHorario;
import com.api.Polimark.dto.RangoHorario;
import com.api.Polimark.modelo.Funcion;
import com.api.Polimark.service.FuncionService;
import com.api.Polimark.service.PeliculaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reporte")
@CrossOrigin(origins = "*")
public class ReporteControler {

    private final PeliculaService peliculaService;

    private final FuncionService funcionService;

    public ReporteControler(PeliculaService peliculaService,FuncionService funcionService) {
        this.peliculaService = peliculaService;
        this.funcionService = funcionService;
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
    @GetMapping("/ocupacion/por-horario")
    public List<Map<RangoHorario, Double>> ocupacionPorRango(
            @RequestParam(required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,

            @RequestParam(required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String nombreEstablecimiento
    ) {
        return funcionService.ocupacionPorRangoEnLugar(desde, hasta,nombreEstablecimiento);
    }
}
