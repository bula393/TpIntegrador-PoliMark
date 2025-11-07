package com.api.Polimark.dto;

import com.api.Polimark.modelo.Lugar;
import jakarta.persistence.*;

public class SalaVisible {

        private int idSala;

        private String capacidad;

        private String tipo;
        private String nombreLugar;


    public SalaVisible(int idSala, String capacidad, String tipo, String nombreLugar) {
        this.idSala = idSala;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.nombreLugar = nombreLugar;
    }

    // Getters y setters
        public int getIdSala() {
            return idSala;
        }

        public void setIdSala(int idSala) {
            this.idSala = idSala;
        }

        public String getCapacidad() {
            return capacidad;
        }

        public void setCapacidad(String capacidad) {
            this.capacidad = capacidad;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getLugar() {
            return nombreLugar;
        }

        public void setLugar(String lugar) {
            this.nombreLugar = lugar;
        }

}
