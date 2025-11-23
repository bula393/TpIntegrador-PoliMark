package com.api.Polimark.controler;

import com.api.Polimark.modelo.Funcion;
import com.api.Polimark.modelo.Pelicula;
import com.api.Polimark.service.FuncionService;
import com.api.Polimark.service.PeliculaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?>  cartelera(
    ) {
       try {
           return ResponseEntity.ok(peliculaService.peliculasConFuncionesProximamente());
       }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idPelicula}/fechas")
    public List<LocalDate> fechasDisponibles(@PathVariable String idPelicula
    ) {
        return peliculaService.findFechaDisponibleById(idPelicula);
    }
    @GetMapping("/{id}/horarios")
    public List<LocalTime> fechasDisponibles(@PathVariable String nombrePelicula,@RequestBody LocalDate fecha) {
        return peliculaService.findHorariosDisponible(nombrePelicula,fecha);
    }
}
