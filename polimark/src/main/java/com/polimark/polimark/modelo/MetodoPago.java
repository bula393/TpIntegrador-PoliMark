package com.polimark.Polimark.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "metodoPago")
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmetodoPago")
    private int idmetodoPago;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "`descuento(%)`") // Se usan backticks porque el nombre contiene caracteres especiales
    private Integer descuento;

    // Constructor vacío
    public MetodoPago() {}

    // Constructor con todos los campos
    public MetodoPago(String nombre, Integer descuento) {
        this.nombre = nombre;
        this.descuento = descuento;
    }

    // Getters y setters
    public int getIdmetodoPago() {
        return idmetodoPago;
    }

    public void setIdmetodoPago(int idmetodoPago) {
        this.idmetodoPago = idmetodoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "MetodoPago{" +
                "idmetodoPago=" + idmetodoPago +
                ", nombre='" + nombre + '\'' +
                ", descuento=" + descuento +
                '}';
    }
}
