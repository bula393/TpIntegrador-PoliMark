package com.api.Polimark.dto;

import com.api.Polimark.modelo.Pelicula;
import com.api.Polimark.modelo.Sala;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class FuncionVisible {

        private Integer idFuncion;
        private LocalDateTime horario;
        private SalaVisible sala;
        private Pelicula pelicula;

        public FuncionVisible(Integer idFuncion,LocalDateTime horario, SalaVisible sala, Pelicula pelicula) {
            this.idFuncion = idFuncion;
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

        public SalaVisible getSala() {
            return sala;
        }

        public void setSala(SalaVisible sala) {
            this.sala = sala;
        }

        public Pelicula getPelicula() {
            return pelicula;
        }

        public void setPelicula(Pelicula pelicula) {
            this.pelicula = pelicula;
        }
    }

