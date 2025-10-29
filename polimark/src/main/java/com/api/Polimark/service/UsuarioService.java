package com.api.Polimark.service;

import com.api.Polimark.dto.Perfil;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository userRepository;

    public UsuarioService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Perfil obtenerPerfil(int idcliente) {
        Usuario usuario = userRepository.findById(idcliente)
                .orElseThrow(() -> new RuntimeException("cliente no existente"));
        List<Entrada> historialEntradas = userRepository.findHistorialById(idcliente);
        Perfil perfil = new Perfil(usuario,historialEntradas);
        return perfil;
    }
}

