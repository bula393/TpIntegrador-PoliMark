package com.api.Polimark.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "articulo")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idarticulo")
    private int idArticulo;

    @Column(name = "precio")
    private Integer precio;

    // Constructor vacío
    public Articulo() {}

    // Constructor con parámetros
    public Articulo(Integer precio) {
        this.precio = precio;
    }

    // Getters y Setters
    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    // toString (opcional)
    @Override
    public String toString() {
        return "Articulo{" +
                "idArticulo=" + idArticulo +
                ", precio=" + precio +
                '}';
    }
}
