package com.api.Polimark.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "sala")
public class Sala {

    @EmbeddedId
    private SalaId id;

    @Column(name = "capacidad")
    private String capacidad;

    @Column(name = "tipo")
    private TipoSala tipo;

    @MapsId("lugarIdLugar") // referencia la parte de la clave compuesta
    @ManyToOne
    @JoinColumn(name = "Lugar_idLugar", referencedColumnName = "idLugar")
    private Lugar lugar;

    public Sala() {}

    // Getters y setters
    public SalaId getId() { return id; }
    public void setId(SalaId id) { this.id = id; }

    public String getCapacidad() { return capacidad; }
    public void setCapacidad(String capacidad) { this.capacidad = capacidad; }

    public TipoSala getTipo() { return tipo; }
    public void setTipo(TipoSala tipo) { this.tipo = tipo; }

    public Lugar getLugar() { return lugar; }
    public void setLugar(Lugar lugar) { this.lugar = lugar; }
}