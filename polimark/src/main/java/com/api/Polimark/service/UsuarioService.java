package com.api.Polimark.service;

import com.api.Polimark.dto.Perfil;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CompraRepository compraRepository;
    private final EntradaRepository entradaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          CompraRepository compraRepository,
                          EntradaRepository entradaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.compraRepository = compraRepository;
        this.entradaRepository = entradaRepository;
    }

    public Perfil obtenerPerfil(int idCliente) {
        // Obtener el usuario
        Usuario usuario = usuarioRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no existente"));

        // Obtener el historial de entradas usando la lógica en el servicio
        List<Entrada> historialEntradas = obtenerHistorialEntradas(idCliente);

        return new Perfil(usuario, historialEntradas);
    }

    private List<Entrada> obtenerHistorialEntradas(Integer idCliente) {
        List<Entrada> historialEntradas = new ArrayList<>();

        // 1. Obtener todas las compras del usuario
        List<Compra> compras = compraRepository.findByUsuarioId(idCliente);

        // 2. Para cada compra, obtener sus entradas
        for (Compra compra : compras) {
            List<Entrada> entradasDeCompra = entradaRepository.findByCompraId(compra.getIdcompra());
            historialEntradas.addAll(entradasDeCompra);
        }
        return historialEntradas;
    }
}

