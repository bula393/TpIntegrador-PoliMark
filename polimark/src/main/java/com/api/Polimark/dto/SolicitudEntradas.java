package com.api.Polimark.dto;
import java.util.List;
import java.util.Map;

public class SolicitudEntradas {
    private List<Map<Integer, Integer>> articulosPromociones;
    private int idUsuario;

    // Constructores
    public SolicitudEntradas() {}

    public SolicitudEntradas(List<Map<Integer, Integer>> articulosPromociones, int idUsuario) {
        this.articulosPromociones = articulosPromociones;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public List<Map<Integer, Integer>> getArticulosPromociones() {
        return articulosPromociones;
    }

    public void setArticulosPromociones(List<Map<Integer, Integer>> articulosPromociones) {
        this.articulosPromociones = articulosPromociones;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


}