package com.api.Polimark.dto;

import java.time.LocalTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RangoHorario)) return false;
        RangoHorario that = (RangoHorario) o;
        return Objects.equals(desde, that.desde) && Objects.equals(hasta, that.hasta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desde, hasta);
    }

    @Override
    public String toString() {
        return desde + " - " + hasta;
    }
}
