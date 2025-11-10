package com.api.Polimark.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompraHasPromocionId implements Serializable {

    @Column(name = "compraIdCompra")
    private int compraIdCompra;

    @Column(name = "promocionesIdPromociones")
    private int promocionesIdPromociones;

    // 🔹 Constructores
    public CompraHasPromocionId() {}

    public CompraHasPromocionId(int compraIdCompra, int promocionesIdPromociones) {
        this.compraIdCompra = compraIdCompra;
        this.promocionesIdPromociones = promocionesIdPromociones;
    }

    // 🔹 Getters y Setters
    public int getCompraIdCompra() {
        return compraIdCompra;
    }

    public void setCompraIdCompra(int compraIdCompra) {
        this.compraIdCompra = compraIdCompra;
    }

    public int getPromocionesIdPromociones() {
        return promocionesIdPromociones;
    }

    public void setPromocionesIdPromociones(int promocionesIdPromociones) {
        this.promocionesIdPromociones = promocionesIdPromociones;
    }

    // 🔹 equals() y hashCode() (obligatorios para claves compuestas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompraHasPromocionId)) return false;
        CompraHasPromocionId that = (CompraHasPromocionId) o;
        return compraIdCompra == that.compraIdCompra &&
                promocionesIdPromociones == that.promocionesIdPromociones;
    }

    @Override
    public int hashCode() {
        return Objects.hash(compraIdCompra, promocionesIdPromociones);
    }
}
