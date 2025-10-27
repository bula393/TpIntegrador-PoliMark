package com.api.Polimark.dto;

import java.util.List;
import java.util.Map;

public class ArticuloPromocionRequest {
    private List<Map<Integer, Integer>> articulosPromociones;

    public List<Map<Integer, Integer>> getArticulosPromociones() {
        return articulosPromociones;
    }

    public void setArticulosPromociones(List<Map<Integer, Integer>> articulosPromociones) {
        this.articulosPromociones = articulosPromociones;
    }
}