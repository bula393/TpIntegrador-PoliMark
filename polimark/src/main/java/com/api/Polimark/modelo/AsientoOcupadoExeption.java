package com.api.Polimark.modelo;

public class AsientoOcupadoExeption extends RuntimeException {
    public AsientoOcupadoExeption() {
        super("haciento ocupado");
    }
}
