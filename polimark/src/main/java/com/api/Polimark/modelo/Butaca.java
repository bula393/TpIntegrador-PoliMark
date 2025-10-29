package com.api.Polimark.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "butaca")
public class Butaca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idButaca")
    private int idbutaca;
    @Column(name = "columna")
    private Integer columna;
    @Column(name = "fila")
    private String fila;

    @ManyToOne
    @JoinColumn(name = "salaIdSala")
    private Sala sala;

    // ✅ Constructores
    public Butaca() {}

    public Butaca(Integer columna, String fila, Sala sala) {
        this.columna = columna;
        this.fila = fila;
        this.sala = sala;
    }

    // ✅ Getters y Setters
    public int getIdbutaca() {
        return idbutaca;
    }

    public void setIdbutaca(int idbutaca) {
        this.idbutaca = idbutaca;
    }

    public Integer getColumna() {
        return columna;
    }

    public void setColumna(Integer columna) {
        this.columna = columna;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
