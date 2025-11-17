package com.api.Polimark.modelo;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductoHasCompraId implements Serializable {

    private Integer compraIdCompra;
    private Integer productoArticuloIdArticulo;

    // Constructores
    public ProductoHasCompraId() {}

    public ProductoHasCompraId(Integer compraIdCompra, Integer productoArticuloIdArticulo) {
        this.compraIdCompra = compraIdCompra;
        this.productoArticuloIdArticulo = productoArticuloIdArticulo;
    }

    // Getters y Setters
    public Integer getCompraIdCompra() {
        return compraIdCompra;
    }

    public void setCompraIdCompra(Integer compraIdCompra) {
        this.compraIdCompra = compraIdCompra;
    }

    public Integer getProductoArticuloIdArticulo() {
        return productoArticuloIdArticulo;
    }

    public void setProductoArticuloIdArticulo(Integer productoArticuloIdArticulo) {
        this.productoArticuloIdArticulo = productoArticuloIdArticulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductoHasCompraId)) return false;
        ProductoHasCompraId that = (ProductoHasCompraId) o;
        return Objects.equals(compraIdCompra, that.compraIdCompra) &&
                Objects.equals(productoArticuloIdArticulo, that.productoArticuloIdArticulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compraIdCompra, productoArticuloIdArticulo);
    }
}