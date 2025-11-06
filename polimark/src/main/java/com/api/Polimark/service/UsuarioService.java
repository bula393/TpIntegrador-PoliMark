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

    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario darRangoCliente(int idCliente,Rango rango){
        Usuario usuario = usuarioRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no existente"));
        usuario.setRango(rango);
        return usuarioRepository.save(usuario);
    }

    public Usuario usuarioLogueado(int idCliente,String contrasenia){
        Usuario usuario = usuarioRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no existente"));
        if (usuario.getContrasenaHash().equals(generarHash(contrasenia))){
            return usuario;
        }
        throw new ContraseniaIncorrectaExeptiom();
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

        List<Compra> compras = compraRepository.findByUsuarioIdentificador(idCliente);

        for (Compra compra : compras) {
            List<Entrada> entradasDeCompra = entradaRepository.findByCompraIdCompra(compra.getIdCompra());
            historialEntradas.addAll(entradasDeCompra);
        }
        return historialEntradas;
    }
}

