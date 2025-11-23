package com.api.Polimark.service;

import com.api.Polimark.dto.ButacaEstado;
import com.api.Polimark.dto.ButacaVisible;
import com.api.Polimark.dto.FuncionVisible;
import com.api.Polimark.dto.SalaVisible;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LugarService {

    private final LugarRepository lugarRepository;

    public LugarService(LugarRepository lugarRepository) {
        this.lugarRepository = lugarRepository;
    }


    public ArrayList<String> nombresSedes() {
        List<Lugar> lugares = lugarRepository.findAll();
        ArrayList<String> nombresSedes = new ArrayList<>();
        for (Lugar lugar : lugares) {
            nombresSedes.add(lugar.getNombre());
        }
        return nombresSedes;
    }
}

