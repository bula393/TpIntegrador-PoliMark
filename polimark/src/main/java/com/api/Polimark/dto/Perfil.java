package com.api.Polimark.dto;

import com.api.Polimark.modelo.*;

import java.util.List;

public class Perfil {
    private Usuario usuario;
    private List<Entrada> historialEntradas;

    public Perfil(Usuario usuario, List<Entrada> historialEntradas) {
        this.usuario = usuario;
        this.historialEntradas = historialEntradas;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {}
}
