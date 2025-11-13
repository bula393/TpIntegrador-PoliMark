package com.api.Polimark.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "producto_has_compra")
public class ProductoHasCompra implements Serializable {

    @EmbeddedId
    private ProductoHasCompraId id;

    @ManyToOne
    @MapsId("compraIdCompra")
    @JoinColumn(name = "compraIdCompra", referencedColumnName = "idcompra")
    private Compra compra;

    @ManyToOne
    @MapsId("productoArticuloIdArticulo")
    @JoinColumn(name = "productoArticuloIdArticulo", referencedColumnName = "articuloIdArticulo")
    private Producto producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    // 🔹 Constructores
    public ProductoHasCompra() {}

    public ProductoHasCompra(Compra compra, Producto producto, Integer cantidad) {
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.id = new ProductoHasCompraId(compra.getIdCompra(), producto.getArticuloIdArticulo());
    }

    // 🔹 Getters y Setters
    public ProductoHasCompraId getId() {
        return id;
    }

    public void setId(ProductoHasCompraId id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ProductoHasCompra{" +
                "compra=" + (compra != null ? compra.getIdCompra() : null) +
                ", producto=" + (producto != null ? producto.getArticuloIdArticulo() : null) +
                ", cantidad=" + cantidad +
                '}';
    }
}
