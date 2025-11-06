package com.api.Polimark.service;

import com.api.Polimark.dto.ButacaEstado;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
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

        // Usar el ID de la sala en lugar de la entidad completa
        List<Butaca> butacas = butacaRepository.findBySalaIdSala(funcion.getSala().getIdSala());

        List<ButacaEstado> mapa = new ArrayList<>();

        for (Butaca butaca : butacas) {
            // Usar IDs para la consulta (más eficiente)
            boolean ocupada = entradaRepository.existsByFuncionIdFuncionAndButacaIdButaca(funcionId, butaca.getIdButaca());
            EstadoOcupacion estado = ocupada ? EstadoOcupacion.OCUPADO : EstadoOcupacion.LIBRE;
            mapa.add(new ButacaEstado(estado, butaca.getIdButaca(), funcion));
        }

        return mapa;
    }
}

