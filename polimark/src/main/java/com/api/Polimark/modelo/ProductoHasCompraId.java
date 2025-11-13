package com.api.Polimark.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductoHasCompraId implements Serializable {

    @Column(name = "compraIdCompra")
    private int compraIdCompra;

    @Column(name = "productoArticuloIdArticulo")
    private int productoArticuloIdArticulo;

    // 🔹 Constructores
    public ProductoHasCompraId() {}

    public ProductoHasCompraId(int compraIdCompra, int productoArticuloIdArticulo) {
        this.compraIdCompra = compraIdCompra;
        this.productoArticuloIdArticulo = productoArticuloIdArticulo;
    }

    // 🔹 Getters y Setters
    public int getCompraIdCompra() {
        return compraIdCompra;
    }

    public void setCompraIdCompra(int compraIdCompra) {
        this.compraIdCompra = compraIdCompra;
    }

    public int getProductoArticuloIdArticulo() {
        return productoArticuloIdArticulo;
    }

    public void setProductoArticuloIdArticulo(int productoArticuloIdArticulo) {
        this.productoArticuloIdArticulo = productoArticuloIdArticulo;
    }

    // 🔹 equals() y hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductoHasCompraId)) return false;
        ProductoHasCompraId that = (ProductoHasCompraId) o;
        return compraIdCompra == that.compraIdCompra &&
                productoArticuloIdArticulo == that.productoArticuloIdArticulo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(compraIdCompra, productoArticuloIdArticulo);
    }
}
