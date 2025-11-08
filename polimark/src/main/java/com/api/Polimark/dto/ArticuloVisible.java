package com.api.Polimark.dto;

import jakarta.persistence.*;

public class ArticuloVisible {

        private int idArticulo;

        private Integer precio;

        // Constructor vacío
        public ArticuloVisible() {}

        // Constructor con parámetros
        public ArticuloVisible(Integer precio) {
            this.precio = precio;
        }

        // Getters y Setters
        public int getIdArticulo() {
            return idArticulo;
        }

        public void setIdArticulo(int idArticulo) {
            this.idArticulo = idArticulo;
        }

        public Integer getPrecio() {
            return precio;
        }

        public void setPrecio(Integer precio) {
            this.precio = precio;
        }


    }


