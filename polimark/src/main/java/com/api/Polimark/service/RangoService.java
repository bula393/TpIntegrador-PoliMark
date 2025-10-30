package com.api.Polimark.service;

import com.api.Polimark.dto.Perfil;
import com.api.Polimark.modelo.*;
        import com.api.Polimark.repository.*;
        import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RangoService {

    private final RangoRepository rangoRepository;


    public RangoService(RangoRepository rangoRepository) {
        this.rangoRepository = rangoRepository;
    }

    public List<Rango> rangos(){
        return rangoRepository.findAll();
    }

    
}


