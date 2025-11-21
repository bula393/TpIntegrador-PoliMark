package com.api.Polimark.dto;

import com.api.Polimark.modelo.Butaca;
import com.api.Polimark.modelo.Funcion;

public class EntradaVisible {
    private Integer idArticulo;
    private Integer precio;
    private ButacaVisible butaca;
    private FuncionVisible funcion;

    public EntradaVisible(Integer idArticulo, Integer precio, ButacaVisible butaca, FuncionVisible funcion) {
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
    public ButacaVisible getButaca() { return butaca; }
    public void setButaca(ButacaVisible butaca) { this.butaca = butaca; }
    public FuncionVisible getFuncion() { return funcion; }
    public void setFuncion(FuncionVisible funcion) { this.funcion = funcion; }
}