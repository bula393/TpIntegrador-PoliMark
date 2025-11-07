package com.api.Polimark.dto;

import com.api.Polimark.modelo.Butaca;
import com.api.Polimark.modelo.EstadoOcupacion;
import com.api.Polimark.modelo.Funcion;

public class ButacaEstado {

    private EstadoOcupacion estadoOcupacion;
    private ButacaVisible butaca;
    private int idFuncion;

    // Constructor vacío
    public ButacaEstado() {}

    // Constructor completo
    public ButacaEstado(EstadoOcupacion estadoOcupacion, int idFuncion, ButacaVisible butaca) {
        this.estadoOcupacion = estadoOcupacion;
        this.idFuncion = idFuncion;
        this.butaca = butaca;
    }

    // Constructor solo con estado y butaca


    // Getters
    public EstadoOcupacion getEstadoOcupacion() {
        return estadoOcupacion;
    }


    public void setEstadoOcupacion(EstadoOcupacion estadoOcupacion) {
        this.estadoOcupacion = estadoOcupacion;
    }

    public ButacaVisible getButaca() {
        return butaca;
    }

    public void setButaca(ButacaVisible butaca) {
        this.butaca = butaca;
    }

    public int getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }
}
