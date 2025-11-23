package com.api.Polimark.service;

import com.api.Polimark.dto.PeliculaHorario;
import com.api.Polimark.dto.RangoHorario;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final FuncionRepository funcionRepository;
    private final EntradaRepository entradaRepository;
    private final CompraRepository compraRepository;

    public PeliculaService(PeliculaRepository peliculaRepository,
                           FuncionRepository funcionRepository,
                           EntradaRepository entradaRepository,
                           CompraRepository compraRepository) {
        this.peliculaRepository = peliculaRepository;
        this.funcionRepository = funcionRepository;
        this.entradaRepository = entradaRepository;
        this.compraRepository = compraRepository;
    }



    public List<Pelicula> peliculasConFuncionesProximamente() {
        LocalDateTime ahora = LocalDateTime.now();

        return funcionRepository.findDistinctByHorarioAfter(ahora).stream()
                .map(Funcion::getPelicula)
                .distinct()
                .toList();
    }



    public PeliculaHorario peliculaMasTaquillera(LocalDate desde, LocalDate hasta) {
        // 1. Obtener todas las películas
        List<Pelicula> todasLasPeliculas = peliculaRepository.findAll();

        // 2. Convertir fechas a LocalDateTime
        LocalDateTime inicio = desde.atStartOfDay();
        LocalDateTime fin = hasta.atTime(23, 59, 59);

        // 3. Encontrar la película más taquillera
        Pelicula peliculaMasTaquillera = null;
        long maxEntradas = 0;

        for (Pelicula pelicula : todasLasPeliculas) {
            // Obtener funciones de esta película en el rango
            List<Funcion> funciones = funcionRepository.findByPeliculaNombreAndHorarioBetween(
                    pelicula.getNombre(), inicio, fin);

            // Contar entradas vendidas
            long totalEntradas = contarEntradasVendidas(funciones);

            // Verificar si es la más taquillera
            if (totalEntradas > maxEntradas) {
                maxEntradas = totalEntradas;
                peliculaMasTaquillera = pelicula;
            }
        }

        if (peliculaMasTaquillera == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron películas taquilleras");
        }

        // 4. Encontrar el rango horario más visto para esta película
        RangoHorario rangoHorarioMasVista = encontrarRangoHorarioMasVista(
                peliculaMasTaquillera.getNombre(), desde, hasta);

        return new PeliculaHorario(peliculaMasTaquillera, rangoHorarioMasVista);
    }

    private long contarEntradasVendidas(List<Funcion> funciones) {
        long totalEntradas = 0;
        for (Funcion funcion : funciones) {
            totalEntradas += entradaRepository.countByFuncionAndCompraPagadoTrue(funcion);
        }
        return totalEntradas;
    }

    private RangoHorario encontrarRangoHorarioMasVista(String nombrePelicula, LocalDate desde, LocalDate hasta) {
        LocalDateTime inicio = desde.atStartOfDay();
        LocalDateTime fin = hasta.atTime(23, 59, 59);

        // Obtener funciones de la película
        List<Funcion> funciones = funcionRepository.findByPeliculaNombreAndHorarioBetween(
                nombrePelicula, inicio, fin);

        // Contar entradas por hora del día
        Map<Integer, Long> entradasPorHora = new HashMap<>();

        for (Funcion funcion : funciones) {
            int hora = funcion.getHorario().getHour();
            long entradas = entradaRepository.countByFuncionAndCompraPagadoTrue(funcion);

            entradasPorHora.put(hora, entradasPorHora.getOrDefault(hora, 0L) + entradas);
        }

        // Encontrar la hora con más entradas
        int horaMasVista = entradasPorHora.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(14); // Hora por defecto (2 PM) si no hay datos

        // Crear rango horario con LocalTime
        LocalTime horaInicio = LocalTime.of(horaMasVista, 0);
        LocalTime horaFin = LocalTime.of((horaMasVista + 1) % 24, 0);

        return new RangoHorario(horaInicio, horaFin);
    }

    public List<LocalTime> findHorariosDisponible(String nombrePelicula, LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.plusDays(1).atStartOfDay();

        return (List<LocalTime>) funcionRepository.findByPeliculaNombreAndHorarioBetween(nombrePelicula, inicio, fin).stream()
                .map(funcion -> funcion.getHorario().toLocalTime());
    }

    public HashSet<LocalDate> findFechaDisponibleById(String idPelicula) {
        List<Funcion> funcionPelicula  = funcionRepository.findByPeliculaNombre(idPelicula);
        HashSet<LocalDate> fechaDisponible = new HashSet<>();
        for (Funcion funcion : funcionPelicula) {
            fechaDisponible.add(funcion.getHorario().toLocalDate());
        }
        return fechaDisponible;
    }
}