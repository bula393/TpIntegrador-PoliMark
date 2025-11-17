package com.api.Polimark.service;

import com.api.Polimark.dto.EntradaVisible;
import com.api.Polimark.dto.Perfil;
import com.api.Polimark.dto.UsuarioRequest;
import com.api.Polimark.modelo.*;
import com.api.Polimark.repository.*;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

    public Usuario crearUsuario(UsuarioRequest usuarioRequest){
        Usuario usuario = new Usuario(usuarioRequest.getIdentificador(),usuarioRequest.getNombre(),usuarioRequest.getApellido(),usuarioRequest.getMail(),generarHash(usuarioRequest.getContrasena()));
        return usuarioRepository.save(usuario);
    }



    public UsuarioRequest usuarioLogueado(int idCliente,String contrasenia){
        Usuario usuario = usuarioRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no existente"));
        byte[] contraseniaByte = generarHash(contrasenia);
        byte[] contraseniaBytes = Arrays.copyOf(usuario.getContrasenaHash(), contraseniaByte.length);
        if (Arrays.equals(contraseniaBytes,contraseniaByte)){
            return new UsuarioRequest(usuario);
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
    public Usuario darRangoCliente(int idCliente,Rango rango){
        Usuario usuario = usuarioRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no existente"));
        usuario.setRango(rango);
        return usuarioRepository.save(usuario);
    }
    public Perfil obtenerPerfil(int idCliente) {
        Usuario usuario = usuarioRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no existente"));

        List<EntradaVisible> historialEntradas = obtenerHistorialEntradas(idCliente);

        return new Perfil(usuario, historialEntradas);
    }

    private List<EntradaVisible> obtenerHistorialEntradas(Integer idCliente) {
        List<Entrada> historialEntradas = new ArrayList<>();

        List<Compra> compras = compraRepository.findByUsuarioIdentificador(idCliente);

        for (Compra compra : compras) {
            List<Entrada> entradasDeCompra = entradaRepository.findByCompraIdCompra(compra.getIdCompra());
            historialEntradas.addAll(entradasDeCompra);
        }

        List<EntradaVisible> entradaVisibles = new ArrayList<>();

        for (Entrada entrada : historialEntradas){
             entradaVisibles.add(new EntradaVisible(entrada.getIdArticulo(),entrada.getArticulo().getPrecio(),entrada.getButaca(),entrada.getFuncion()));
        }

        return entradaVisibles;
    }
}

