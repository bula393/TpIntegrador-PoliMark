package com.api.Polimark.dto;

public class PagoCompra {
    private int idCompra;
    private int idMetodoPago;

    public PagoCompra(int idCompra, int idMetodoPago) {
        this.idCompra = idCompra;
        this.idMetodoPago = idMetodoPago;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }
}
