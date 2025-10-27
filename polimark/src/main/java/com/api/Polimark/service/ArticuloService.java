package com.api.Polimark.service;

import com.api.Polimark.dto.ButacaEstado;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
@Service
public class ArticuloService {
    private final ArticuloRepository articuloRepository;
    private final PromocionRepository promocionRepository;
    private final PromocionHasArticuloRepository promocionHasArticuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository,
                           PromocionRepository promocionRepository,
                           PromocionHasArticuloRepository promocionHasArticuloRepository) {
        this.articuloRepository = articuloRepository;
        this.promocionRepository = promocionRepository;
        this.promocionHasArticuloRepository = promocionHasArticuloRepository;
    }


}





