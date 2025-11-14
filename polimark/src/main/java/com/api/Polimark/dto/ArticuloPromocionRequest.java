package com.api.Polimark.dto;

import java.util.List;

public class ArticuloPromocionRequest {
    private String tipo; // ENTRADA, PRODUCTO, ENTRADA_AUTO
    private Integer cantidad;
    private Integer precio;
    private Integer funcionId;
    private List<Integer> butacasIds;
    private Integer productoId;
    private String patente;
    private Integer cantidadAuto;
    private List<Integer> promocionesAplicadas;

    // Constructores
    public ArticuloPromocionRequest() {}

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getFuncionId() {
        return funcionId;
    }

    public void setFuncionId(Integer funcionId) {
        this.funcionId = funcionId;
    }

    public List<Integer> getButacasIds() {
        return butacasIds;
    }

    public void setButacasIds(List<Integer> butacasIds) {
        this.butacasIds = butacasIds;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Integer getCantidadAuto() {
        return cantidadAuto;
    }

    public void setCantidadAuto(Integer cantidadAuto) {
        this.cantidadAuto = cantidadAuto;
    }

    public List<Integer> getPromocionesAplicadas() {
        return promocionesAplicadas;
    }

    public void setPromocionesAplicadas(List<Integer> promocionesAplicadas) {
        this.promocionesAplicadas = promocionesAplicadas;
    }
}