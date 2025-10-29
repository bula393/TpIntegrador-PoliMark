package com.api.Polimark.dto;

import java.time.LocalTime;

public class RangoHorario {
    private LocalTime desde;
    private LocalTime hasta;

    public RangoHorario(LocalTime desde, LocalTime hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    public LocalTime getDesde() {
        return desde;
    }

    public void setDesde(LocalTime desde) {
        this.desde = desde;
    }

    public LocalTime getHasta() {
        return hasta;
    }

    public void setHasta(LocalTime hasta) {
        this.hasta = hasta;
    }
}
