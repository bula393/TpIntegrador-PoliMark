package com.api.Polimark.controler;

import com.api.Polimark.modelo.Funcion;
import com.api.Polimark.modelo.Pelicula;
import com.api.Polimark.service.FuncionService;
import com.api.Polimark.service.PeliculaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaControler {

    private final PeliculaService peliculaService;

    public PeliculaControler(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping("/cartelera")
    public List<Pelicula> cartelera(
    ) {
        return peliculaService.peliculasConFuncionesProximamente();
    }

    @GetMapping("/{id}/fechas")
    public List<LocalDate> fechasDisponibles(@PathVariable long idPelicula
    ) {
        return peliculaService.findFechaDisponibleById(idPelicula);
    }
    @GetMapping("/{id}/horarios")
    public List<LocalTime> fechasDisponibles(@PathVariable long idPelicula,@RequestBody LocalDate fecha) {
        return peliculaService.findHorariosDisponible(idPelicula,fecha);
    }
}
