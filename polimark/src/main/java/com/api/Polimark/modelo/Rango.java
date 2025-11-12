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

    // Constructor vacío
    public Rango() {}

    // Constructor con parámetros
    public Rango(String nombre) {
        this.nombre = nombre;
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

