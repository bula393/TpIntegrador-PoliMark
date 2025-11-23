package com.api.Polimark.modelo;
import jakarta.persistence.*;

@Entity
@Table(name = "rango")
public class Rango {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRango")
    private int idRango;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private int precio;

    // Constructor vacío
    public Rango() {}

    public Rango(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    // Getters y Setters
    public int getIdRango() {
        return idRango;
    }

    public void setIdRango(int idRango) {
        this.idRango = idRango;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Rango{" +
                "idRango=" + idRango +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

