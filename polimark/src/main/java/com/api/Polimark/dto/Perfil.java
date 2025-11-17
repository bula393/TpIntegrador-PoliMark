package com.api.Polimark.dto;

import com.api.Polimark.modelo.*;

import java.util.List;

public class Perfil {
    private Usuario usuario;
    private List<EntradaVisible> historialEntradas;

    public Perfil(Usuario usuario, List<EntradaVisible> historialEntradas) {
        this.usuario = usuario;
        this.historialEntradas = historialEntradas;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {}

    public List<EntradaVisible> getHistorialEntradas() {
        return historialEntradas;
    }

    public void setHistorialEntradas(List<EntradaVisible> historialEntradas) {
        this.historialEntradas = historialEntradas;
    }
}
