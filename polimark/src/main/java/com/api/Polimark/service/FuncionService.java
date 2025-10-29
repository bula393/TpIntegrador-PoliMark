package com.api.Polimark.service;

import com.api.Polimark.dto.RangoHorario;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FuncionService {

    private final FuncionRepository funcionRepository;

    public FuncionService(FuncionRepository funcionRepository) {
        this.funcionRepository = funcionRepository;
    }

    public List<Funcion> buscarFunciones(LocalDate fecha, String sucursalNombre, String peliculaNombre) {
        return funcionRepository.buscarFunciones(fecha, sucursalNombre, peliculaNombre);
    }

    public List<Map<RangoHorario, Double>> ocupacionPorRangoEnLugar(LocalDate desde, LocalDate hasta, String nombreEstablecimiento) {
        List<Object[]> resultados = funcionRepository.findOcupacionPorRangoHorario(desde, hasta, nombreEstablecimiento);

        return resultados.stream()
                .map(result -> {
                    RangoHorario rango = (RangoHorario) result[0];
                    Double porcentaje = (Double) result[1];
                    // Redondear a 2 decimales
                    porcentaje = Math.round(porcentaje * 100.0) / 100.0;

                    Map<RangoHorario, Double> mapa = new LinkedHashMap<>();
                    mapa.put(rango, porcentaje);
                    return mapa;
                })
                .toList();

    }
}
