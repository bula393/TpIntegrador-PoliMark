package com.api.Polimark.service;

import com.api.Polimark.dto.RangoHorario;
import com.api.Polimark.modelo.Funcion;
import com.api.Polimark.repository.FuncionRepository;
import com.api.Polimark.repository.EntradaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FuncionService {

    private final FuncionRepository funcionRepository;
    private final EntradaRepository entradaRepository;

    public FuncionService(FuncionRepository funcionRepository, EntradaRepository entradaRepository) {
        this.funcionRepository = funcionRepository;
        this.entradaRepository = entradaRepository;
    }

    public List<Funcion> buscarFunciones(LocalDate fecha, String sucursalNombre, String peliculaNombre) {
        // Traer todas las funciones ordenadas por horario
        List<Funcion> todasLasFunciones = funcionRepository.findAllByOrderByHorario();

        // Filtrar en memoria
        return todasLasFunciones.stream()
                .filter(funcion -> filtrarPorFecha(funcion, fecha))
                .filter(funcion -> filtrarPorSucursal(funcion, sucursalNombre))
                .filter(funcion -> filtrarPorPelicula(funcion, peliculaNombre))
                .collect(Collectors.toList());
    }

    private boolean filtrarPorFecha(Funcion funcion, LocalDate fecha) {
        if (fecha == null) return true;
        return funcion.getHorario().toLocalDate().equals(fecha);
    }

    private boolean filtrarPorSucursal(Funcion funcion, String sucursalNombre) {
        if (sucursalNombre == null || sucursalNombre.trim().isEmpty()) return true;
        return funcion.getSala() != null &&
                funcion.getSala().getLugar() != null &&
                sucursalNombre.equalsIgnoreCase(funcion.getSala().getLugar().getNombre());
    }

    private boolean filtrarPorPelicula(Funcion funcion, String peliculaNombre) {
        if (peliculaNombre == null || peliculaNombre.trim().isEmpty()) return true;
        return funcion.getPelicula() != null &&
                peliculaNombre.equalsIgnoreCase(funcion.getPelicula().getNombre());
    }

    public List<Map<RangoHorario, Double>> ocupacionPorRangoEnLugar(LocalDate desde, LocalDate hasta, String nombreEstablecimiento) {
        // Traer todas las funciones
        List<Funcion> todasLasFunciones = funcionRepository.findAllByOrderByHorario();

        // Filtrar por rango de fechas y establecimiento
        List<Funcion> funcionesFiltradas = todasLasFunciones.stream()
                .filter(funcion -> estaEnRangoFechas(funcion, desde, hasta))
                .filter(funcion -> filtrarPorEstablecimiento(funcion, nombreEstablecimiento))
                .collect(Collectors.toList());

        // Agrupar por hora y calcular ocupación
        Map<Integer, List<Funcion>> funcionesPorHora = funcionesFiltradas.stream()
                .collect(Collectors.groupingBy(funcion -> funcion.getHorario().getHour()));

        // Calcular ocupación promedio por hora
        return funcionesPorHora.entrySet().stream()
                .map(entry -> {
                    int hora = entry.getKey();
                    List<Funcion> funcionesDeEstaHora = entry.getValue();

                    double ocupacionPromedio = calcularOcupacionPromedio(funcionesDeEstaHora);

                    // Crear rango horario
                    RangoHorario rango = new RangoHorario(
                            LocalTime.of(hora, 0),
                            LocalTime.of(hora, 59, 59)
                    );

                    Map<RangoHorario, Double> resultado = new LinkedHashMap<>();
                    resultado.put(rango, Math.round(ocupacionPromedio * 100.0) / 100.0);
                    return resultado;
                })
                .sorted((map1, map2) -> {
                    // Ordenar por hora
                    RangoHorario rango1 = map1.keySet().iterator().next();
                    RangoHorario rango2 = map2.keySet().iterator().next();
                    return rango1.getDesde().compareTo(rango2.getHasta());
                })
                .collect(Collectors.toList());
    }

    private boolean estaEnRangoFechas(Funcion funcion, LocalDate desde, LocalDate hasta) {
        LocalDate fechaFuncion = funcion.getHorario().toLocalDate();
        return !fechaFuncion.isBefore(desde) && !fechaFuncion.isAfter(hasta);
    }

    private boolean filtrarPorEstablecimiento(Funcion funcion, String nombreEstablecimiento) {
        if (nombreEstablecimiento == null || nombreEstablecimiento.trim().isEmpty()) return true;
        return funcion.getSala() != null &&
                funcion.getSala().getLugar() != null &&
                nombreEstablecimiento.equalsIgnoreCase(funcion.getSala().getLugar().getNombre());
    }

    private double calcularOcupacionPromedio(List<Funcion> funciones) {
        if (funciones.isEmpty()) return 0.0;

        double sumaOcupaciones = 0.0;
        int funcionesConCapacidadValida = 0;

        for (Funcion funcion : funciones) {
            try {
                int entradasVendidas = (int) entradaRepository.countByFuncionIdfuncion(funcion.getIdfuncion());
                int capacidad = Integer.parseInt(funcion.getSala().getCapacidad());

                if (capacidad > 0) {
                    double ocupacion = (double) entradasVendidas / capacidad * 100;
                    sumaOcupaciones += ocupacion;
                    funcionesConCapacidadValida++;
                }
            } catch (NumberFormatException e) {
                // Si la capacidad no es un número válido, saltar esta función
                System.err.println("Capacidad inválida para función ID: " + funcion.getIdfuncion());
            }
        }

        return funcionesConCapacidadValida > 0 ? sumaOcupaciones / funcionesConCapacidadValida : 0.0;
    }

    // Método adicional para debugging o estadísticas
    public Map<String, Object> obtenerEstadisticas() {
        List<Funcion> todasLasFunciones = funcionRepository.findAll();

        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("totalFunciones", todasLasFunciones.size());
        stats.put("funcionesConSala", todasLasFunciones.stream().filter(f -> f.getSala() != null).count());
        stats.put("funcionesConPelicula", todasLasFunciones.stream().filter(f -> f.getPelicula() != null).count());
        stats.put("funcionesConLugar", todasLasFunciones.stream()
                .filter(f -> f.getSala() != null && f.getSala().getLugar() != null).count());

        return stats;
    }
}