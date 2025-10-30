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

    public void crearUsuario(int identificador,String nombre, String apellido, String contrasenia,String mail){
        Usuario usuario = new Usuario(identificador,nombre, apellido, generarHash(contrasenia),mail);
        usuarioRepository.save(usuario);
    }

    private byte[] generarHash(String contrasenia){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return md.digest(contrasenia.getBytes());
    }

    public Perfil obtenerPerfil(int idCliente) {
        Usuario usuario = usuarioRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no existente"));

        List<Entrada> historialEntradas = obtenerHistorialEntradas(idCliente);

        return new Perfil(usuario, historialEntradas);
    }

    private List<Entrada> obtenerHistorialEntradas(Integer idCliente) {
        List<Entrada> historialEntradas = new ArrayList<>();

        List<Compra> compras = compraRepository.findByUsuarioId(idCliente);

        for (Compra compra : compras) {
            List<Entrada> entradasDeCompra = entradaRepository.findByCompraId(compra.getIdcompra());
            historialEntradas.addAll(entradasDeCompra);
        }
        return historialEntradas;
    }
}

