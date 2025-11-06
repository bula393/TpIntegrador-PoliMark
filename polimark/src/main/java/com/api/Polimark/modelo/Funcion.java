package com.api.Polimark.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "funcion")
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFuncion") // This matches your DB
    private Integer idFuncion;

    @Column(name = "horario") // This matches your DB
    private LocalDateTime horario;

    // Relación con Sala - FIXED to match DB column name
    @ManyToOne
    @JoinColumn(name = "salaIdSala") // Changed from "salaIdSala" to match exact DB column name
    private Sala sala;

    // Relación con Pelicula - FIXED to match DB column name
    @ManyToOne
    @JoinColumn(name = "peliculaNombre") // This matches your DB
    private Pelicula pelicula;

    public Funcion() {}

    public Funcion(LocalDateTime horario, Sala sala, Pelicula pelicula) {
        this.horario = horario;
        this.sala = sala;
        this.pelicula = pelicula;
    }

    // Getters y Setters
    public Integer getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(Integer idFuncion) {
        this.idFuncion = idFuncion;
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
