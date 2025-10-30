package com.api.Polimark.service;

import com.api.Polimark.dto.PeliculaHorario;
import com.api.Polimark.dto.RangoHorario;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public PeliculaHorario peliculaMasTaquillera(LocalDate desde, LocalDate hasta) {
        Optional<Pelicula> peliculaMasTaquillera;
        RangoHorario rangoHorarioMasVista;
        try {
            peliculaMasTaquillera = findPeliculaMasTaquilleraEntre(desde, hasta);
        }
        catch (Exception e) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Pelicula no encontrada");
        }
        try {
            rangoHorarioMasVista = findRangoHorarioMasVistaEntre(
                    desde, hasta, peliculaMasTaquillera.get().getNombre());
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "rango pelicula no encontrada");
        }

        return (new PeliculaHorario(peliculaMasTaquillera.orElse(null), rangoHorarioMasVista));
    }

    private Optional<Pelicula> findPeliculaMasTaquilleraEntre(LocalDate desde, LocalDate hasta) {
        // Obtener todas las funciones en el rango de fechas
        List<Funcion> funciones = funcionRepository.findAll().stream()
                .filter(funcion -> {
                    LocalDate fechaFuncion = funcion.getHorario().toLocalDate();
                    return !fechaFuncion.isBefore(desde) && !fechaFuncion.isAfter(hasta);
                })
                .collect(Collectors.toList());

        // Contar entradas vendidas por película
        Map<Pelicula, Long> entradasPorPelicula = funciones.stream()
                .collect(Collectors.groupingBy(
                        Funcion::getPelicula,
                        Collectors.summingLong(funcion -> entradaRepository.countByFuncionIdfuncion(funcion.getIdfuncion()))
                ));

        // Encontrar la película con más entradas vendidas
        return entradasPorPelicula.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    private RangoHorario findRangoHorarioMasVistaEntre(LocalDate desde, LocalDate hasta, String nombrePelicula) {
        // Obtener todas las funciones de la película en el rango de fechas
        List<Funcion> funcionesPelicula = funcionRepository.findAll().stream()
                .filter(funcion -> funcion.getPelicula().getNombre().equals(nombrePelicula))
                .filter(funcion -> {
                    LocalDate fechaFuncion = funcion.getHorario().toLocalDate();
                    return !fechaFuncion.isBefore(desde) && !fechaFuncion.isAfter(hasta);
                })
                .collect(Collectors.toList());

        // Agrupar por hora y contar entradas vendidas usando el repository
        Map<Integer, Long> entradasPorHora = funcionesPelicula.stream()
                .collect(Collectors.groupingBy(
                        funcion -> funcion.getHorario().getHour(),
                        Collectors.summingLong(funcion -> contarEntradasVendidasPorFuncion(funcion))
                ));

        // Encontrar la hora con más entradas vendidas
        Optional<Map.Entry<Integer, Long>> horaMasVendida = entradasPorHora.entrySet().stream()
                .max(Map.Entry.comparingByValue());


        int hora = horaMasVendida.get().getKey();

        // Crear el rango horario
        LocalTime horaInicio = LocalTime.of(hora, 0, 0);
        LocalTime horaFin = horaInicio.plusHours(1);

        RangoHorario rangoHorario = new RangoHorario(
                horaInicio,
                horaFin
        );

        return rangoHorario;
    }

    private long contarEntradasVendidasPorFuncion(Funcion funcion) {
        // Contar entradas vendidas para una función específica usando el repository
        return entradaRepository.countByFuncionIdfuncion(funcion.getIdfuncion());
    }

}