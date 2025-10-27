package com.api.Polimark.service;

import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntradaService {
    private final ButacaRepository butacaRepository;
    private final EntradaRepository entradaRepository;


    public ButacaService(ButacaRepository butacaRepository,
                         EntradaRepository entradaRepository,
                         FuncionRepository funcionRepository) {
        this.butacaRepository = butacaRepository;
        this.entradaRepository = entradaRepository;
        this.funcionRepository = funcionRepository;
    }

    public void reservarAsiento(Compra compra,Funcion funcion,Butaca butaca) {

        boolean ocupada = entradaRepository.existsByFuncionAndButaca(funcion, butaca);
        if(ocupada){
            throw new AsientoOcupadoExeption();
        }
        Entrada entradaNueva = new Entrada(compra,funcion,butaca);
        entradaRepository.save(entradaNueva);

    }

}
