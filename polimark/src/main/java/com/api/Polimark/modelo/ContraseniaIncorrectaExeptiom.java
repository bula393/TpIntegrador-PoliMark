package com.api.Polimark.modelo;

public class ContraseniaIncorrectaExeptiom extends RuntimeException {
    public ContraseniaIncorrectaExeptiom() {
        super("contraseña incorrecta");
    }
}
