package com.api.Polimark.dto;

import com.api.Polimark.modelo.Pelicula;

import java.time.LocalTime;

public class PeliculaHorario {
    private Pelicula pelicula;
    private RangoHorario rangoHorario;

    public PeliculaHorario(Pelicula pelicula, RangoHorario rangoHorario) {
        this.pelicula = pelicula;
        this.rangoHorario = rangoHorario;
    }

    public RangoHorario getRangoHorario() {
        return rangoHorario;
    }

    public void setRangoHorario(RangoHorario rangoHorario) {
        this.rangoHorario = rangoHorario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

}
