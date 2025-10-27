package com.polimark.Polimark.dto;

import com.polimark.Polimark.modelo.Butaca;
import com.polimark.Polimark.modelo.EstadoOcupacion;

public class ButacaEstado {

    private EstadoOcupacion estadoOcupacion;
    private Butaca butaca;
    private int idFuncion;

    // Constructor vacío
    public ButacaEstado() {}

    // Constructor completo
    public ButacaEstado(EstadoOcupacion estadoOcupacion, Butaca butaca, int idFuncion) {
        this.estadoOcupacion = estadoOcupacion;
        this.butaca = butaca;
        this.idFuncion = idFuncion;
    }

    // Constructor solo con estado y butaca
    public ButacaEstado(EstadoOcupacion estadoOcupacion, Butaca butaca) {
        this.estadoOcupacion = estadoOcupacion;
        this.butaca = butaca;
    }

    // Getters
    public EstadoOcupacion getEstadoOcupacion() {
        return estadoOcupacion;
    }

    public Butaca getButaca() {
        return butaca;
    }

    public int getIdFuncion() {
        return idFuncion;
    }

    // Setters
    public void setEstadoOcupacion(EstadoOcupacion estadoOcupacion) {
        this.estadoOcupacion = estadoOcupacion;
    }

    public void setButaca(Butaca butaca) {
        this.butaca = butaca;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }

    @Override
    public String toString() {
        return "ButacaEstado{" +
                "estadoOcupacion=" + estadoOcupacion +
                ", butaca=" + butaca +
                ", idFuncion=" + idFuncion +
                '}';
    }
}
