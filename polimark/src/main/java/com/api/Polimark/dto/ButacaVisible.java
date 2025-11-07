package com.api.Polimark.dto;

import com.api.Polimark.modelo.Sala;
import jakarta.persistence.*;

public class ButacaVisible {
        private int idButaca;

        private Integer columna;

        private String fila;


        private SalaVisible sala;



        public ButacaVisible(int idButaca,Integer columna, String fila, SalaVisible sala) {
            this.columna = columna;
            this.fila = fila;
            this.sala = sala;
        }

        // ✅ Getters y Setters
        public int getIdButaca() {
            return idButaca;
        }

        public void setIdButaca(int idbutaca) {
            this.idButaca = idButaca;
        }

        public Integer getColumna() {
            return columna;
        }

        public void setColumna(Integer columna) {
            this.columna = columna;
        }

        public String getFila() {
            return fila;
        }

        public void setFila(String fila) {
            this.fila = fila;
        }

        public SalaVisible getSala() {
            return sala;
        }

        public void setSala(SalaVisible sala) {
            this.sala = sala;
        }

}
