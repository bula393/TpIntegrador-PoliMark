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


    public EntradaService(ButacaRepository butacaRepository,
                         EntradaRepository entradaRepository
    ) {
        this.butacaRepository = butacaRepository;
        this.entradaRepository = entradaRepository;
    }

    public void reservarAsiento(Compra compra,Articulo articulo,Funcion funcion,Butaca butaca) {

        boolean ocupada = entradaRepository.existsByFuncionAndButaca(funcion, butaca);
        if(ocupada){
            throw new AsientoOcupadoExeption();
        }
        Entrada entradaNueva = new Entrada(articulo.getIdArticulo(),articulo,compra,funcion,butaca);
        entradaRepository.save(entradaNueva);

    }

}
