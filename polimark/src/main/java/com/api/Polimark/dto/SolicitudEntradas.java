package com.api.Polimark.dto;
import java.util.List;
import java.util.Map;

public class SolicitudEntradas {
    private List<ArticuloPromocionRequest> articulosPromociones;
    private int idUsuario;

    // Constructores
    public SolicitudEntradas() {}

    public SolicitudEntradas(List<ArticuloPromocionRequest> articulosPromociones, int idUsuario) {
        this.articulosPromociones = articulosPromociones;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public List<ArticuloPromocionRequest> getArticulosPromociones() {
        return articulosPromociones;
    }

    public void setArticulosPromociones(List<ArticuloPromocionRequest> articulosPromociones) {
        this.articulosPromociones = articulosPromociones;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


}