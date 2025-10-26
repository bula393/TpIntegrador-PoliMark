package com.polimark.polimark.service;

import com.polimark.polimark.model.*;
import com.polimark.polimark.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ButacaService {

    private final ButacaRepository butacaRepository;
    private final EntradaRepository entradaRepository;
    private final FuncionRepository funcionRepository;

    public ButacaService(ButacaRepository butacaRepository,
                         EntradaRepository entradaRepository,
                         FuncionRepository funcionRepository) {
        this.butacaRepository = butacaRepository;
        this.entradaRepository = entradaRepository;
        this.funcionRepository = funcionRepository;
    }

    public List<ButacaEstado> obtenerMapaButacas(int funcionId) {
        Funcion funcion = funcionRepository.findById(funcionId)
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));

        // Traer todas las butacas de la sala de la función
        List<Butaca> butacas = butacaRepository.findBySala(funcion.getSala());

        List<ButacaEstado> mapa = new ArrayList<>();

        for (Butaca b : butacas) {
            boolean ocupada = entradaRepository.existsByFuncionAndButaca(funcion, b);
            EstadoOcupacion estado = ocupada ? EstadoOcupacion.OCUPADA : EstadoOcupacion.LIBRE;

            mapa.add(new ButacaEstado(b.getIdbutaca(), funcion, estado));
        }

        return mapa;
    }
}

