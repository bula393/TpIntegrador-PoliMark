package com.polimark.polimark.service;

import com.web.cine.model.Funcion;
import com.web.cine.repository.FuncionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FuncionService {

    private final FuncionRepository funcionRepository;

    public FuncionService(FuncionRepository funcionRepository) {
        this.funcionRepository = funcionRepository;
    }

    public List<Funcion> buscarFunciones(LocalDate fecha, String sucursalNombre, String peliculaNombre) {
        return funcionRepository.buscarFunciones(fecha, sucursalNombre, peliculaNombre);
    }
}
