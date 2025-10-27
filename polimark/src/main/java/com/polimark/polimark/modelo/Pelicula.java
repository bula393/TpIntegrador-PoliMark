package com.polimark.Polimark.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Pelicula")
public class Pelicula {

    @Id
    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "productor", length = 45)
    private String productor;

    // El nombre del campo tiene paréntesis en SQL, así que lo renombramos en el modelo
    @Column(name = "duracion(min)")
    private Integer duracionMin;

    public Pelicula() {}

    public Pelicula(String nombre, String productor, Integer duracionMin) {
        this.nombre = nombre;
        this.productor = productor;
        this.duracionMin = duracionMin;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getProductor() { return productor; }
    public void setProductor(String productor) { this.productor = productor; }

    public Integer getDuracionMin() { return duracionMin; }
    public void setDuracionMin(Integer duracionMin) { this.duracionMin = duracionMin; }

}
