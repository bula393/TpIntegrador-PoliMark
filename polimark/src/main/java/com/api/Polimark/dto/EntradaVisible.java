package com.api.Polimark.dto;

import com.api.Polimark.modelo.Butaca;
import com.api.Polimark.modelo.Funcion;

public class EntradaVisible {
    private Integer idArticulo;
    private Integer precio;
    private Butaca butaca;
    private Funcion funcion;

    public EntradaVisible(Integer idArticulo, Integer precio, Butaca butaca, Funcion funcion) {
        this.idArticulo = idArticulo;
        this.precio = precio;
        this.butaca = butaca;
        this.funcion = funcion;
    }

    // Getters y Setters
    public Integer getIdArticulo() { return idArticulo; }
    public void setIdArticulo(Integer idArticulo) { this.idArticulo = idArticulo; }
    public Integer getPrecio() { return precio; }
    public void setPrecio(Integer precio) { this.precio = precio; }
    public Butaca getButaca() { return butaca; }
    public void setButaca(Butaca butaca) { this.butaca = butaca; }
    public Funcion getFuncion() { return funcion; }
    public void setFuncion(Funcion funcion) { this.funcion = funcion; }
}