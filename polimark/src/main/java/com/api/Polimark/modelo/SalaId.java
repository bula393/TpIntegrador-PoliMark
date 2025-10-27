package com.api.Polimark.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SalaId implements Serializable {

    private Integer idsala;
    private Integer lugarIdLugar;

    public SalaId() {}

    public SalaId(Integer idsala, Integer lugarIdLugar) {
        this.idsala = idsala;
        this.lugarIdLugar = lugarIdLugar;
    }

    // Getters y setters
    public Integer getIdsala() { return idsala; }
    public void setIdsala(Integer idsala) { this.idsala = idsala; }

    public Integer getLugarIdLugar() { return lugarIdLugar; }
    public void setLugarIdLugar(Integer lugarIdLugar) { this.lugarIdLugar = lugarIdLugar; }

    // hashCode y equals obligatorios
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalaId)) return false;
        SalaId salaId = (SalaId) o;
        return Objects.equals(idsala, salaId.idsala) &&
               Objects.equals(lugarIdLugar, salaId.lugarIdLugar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsala, lugarIdLugar);
    }
}