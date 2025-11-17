package com.api.Polimark.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "producto_has_compra")
public class ProductoHasCompra {

    @EmbeddedId
    private ProductoHasCompraId id;

    @Column(name = "cantidad")
    private Integer cantidad;

    @ManyToOne
    @MapsId("compraIdCompra")
    @JoinColumn(name = "compraIdCompra")
    private Compra compra;

    @ManyToOne
    @MapsId("productoArticuloIdArticulo")
    @JoinColumn(name = "productoArticuloIdArticulo")
    private Producto producto;

    // Constructores
    public ProductoHasCompra() {}

    public ProductoHasCompra(Compra compra, Producto producto, Integer cantidad) {
        this.compra = compra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.id = new ProductoHasCompraId(compra.getIdCompra(), producto.getArticuloIdArticulo());
    }

    // Getters y Setters
    public ProductoHasCompraId getId() {
        return id;
    }

    public void setId(ProductoHasCompraId id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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
}