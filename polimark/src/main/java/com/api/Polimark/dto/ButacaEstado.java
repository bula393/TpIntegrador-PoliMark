package com.api.Polimark.dto;

import com.api.Polimark.modelo.Butaca;
import com.api.Polimark.modelo.EstadoOcupacion;
import com.api.Polimark.modelo.Funcion;

public class ButacaEstado {

    private EstadoOcupacion estadoOcupacion;
    private int idButaca;
    private Funcion funcion;

    // Constructor vacío
    public ButacaEstado() {}

    // Constructor completo
    public ButacaEstado(EstadoOcupacion estadoOcupacion, int idButaca, Funcion funcion) {
        this.estadoOcupacion = estadoOcupacion;
        this.funcion = funcion;
        this.idButaca = idButaca;
    }

    // Constructor solo con estado y butaca


    // Getters
    public EstadoOcupacion getEstadoOcupacion() {
        return estadoOcupacion;
    }


    public void setEstadoOcupacion(EstadoOcupacion estadoOcupacion) {
        this.estadoOcupacion = estadoOcupacion;
    }

    public int getIdButaca() {
        return idButaca;
    }

    public void setIdButaca(int idButaca) {
        this.idButaca = idButaca;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }
}
