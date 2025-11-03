package com.api.Polimark.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "productor", length = 45)
    private String productor;

    @Column(name = "duracionMin")
    private Integer duracionMin;

    @Column(name = "fotoCartelera", length = 100)
    private String fotoCartelera;

    @Column(name = "linkTrailer", length = 200)
    private String linkTrailer;

    // 🔹 Constructores
    public Pelicula() {}

    public Pelicula(String nombre, String productor, Integer duracionMin, String fotoCartelera, String linkTrailer) {
        this.nombre = nombre;
        this.productor = productor;
        this.duracionMin = duracionMin;
        this.fotoCartelera = fotoCartelera;
        this.linkTrailer = linkTrailer;
    }

    // 🔹 Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getProductor() { return productor; }
    public void setProductor(String productor) { this.productor = productor; }

    public Integer getDuracionMin() { return duracionMin; }
    public void setDuracionMin(Integer duracionMin) { this.duracionMin = duracionMin; }

    public String getFotoCartelera() { return fotoCartelera; }
    public void setFotoCartelera(String fotoCartelera) { this.fotoCartelera = fotoCartelera; }

    public String getLinkTrailer() { return linkTrailer; }
    public void setLinkTrailer(String linkTrailer) { this.linkTrailer = linkTrailer; }

    @Override
    public String toString() {
        return "Pelicula{" +
                "nombre='" + nombre + '\'' +
                ", productor='" + productor + '\'' +
                ", duracionMin=" + duracionMin +
                ", fotoCartelera='" + fotoCartelera + '\'' +
                ", linkTrailer='" + linkTrailer + '\'' +
                '}';
    }
}
