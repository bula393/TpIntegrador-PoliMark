package com.api.Polimark.modelo;

public class excedeLimiteExeption extends RuntimeException {
    public excedeLimiteExeption() {
        super("supera el limite de entradas");
    }
}
