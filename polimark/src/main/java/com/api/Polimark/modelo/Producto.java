package com.api.Polimark.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @Column(name = "articuloIdArticulo")
    private Integer articuloIdArticulo;

    @OneToOne
    @JoinColumn(name = "articuloIdArticulo", referencedColumnName = "idArticulo", insertable = false, updatable = false)
    private Articulo articulo;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "descripcion", length = 45)
    private String descripcion;

    @Column(name = "categoria", length = 45)
    private String categoria;

    @Column(name = "stock")
    private Integer stock;

    // 🔹 Constructores
    public Producto() {}

    public Producto(String nombre, String descripcion, String categoria, Integer stock, Articulo articulo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.stock = stock;
        this.articulo = articulo;
    }

    // 🔹 Getters y Setters
    public Integer getArticuloIdArticulo() {
        return articuloIdArticulo;
    }

    public void setArticuloIdArticulo(int articuloIdArticulo) {
        this.articuloIdArticulo = articuloIdArticulo;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "articuloIdArticulo=" + articuloIdArticulo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", stock=" + stock +
                '}';
    }
}
