package com.polimark.Polimark.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "funcion")
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfuncion")
    private Integer idfuncion;

    @Column(name = "horario")
    private LocalDateTime horario;

    // Relación con Sala
    @ManyToOne
    @JoinColumn(name = "sala_idsala", referencedColumnName = "idsala")
    private Sala sala;

    // Relación con Pelicula
    @ManyToOne
    @JoinColumn(name = "Pelicula_nombre", referencedColumnName = "nombre")
    private Pelicula pelicula;

    public Funcion() {}

    public Funcion(LocalDateTime horario, Sala sala, Pelicula pelicula) {
        this.horario = horario;
        this.sala = sala;
        this.pelicula = pelicula;
    }

    // Getters y Setters
    public Integer getIdfuncion() {
        return idfuncion;
    }

    public void setIdfuncion(Integer idfuncion) {
        this.idfuncion = idfuncion;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
