package com.api.Polimark.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "butaca")
public class Butaca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idButaca")
    private int idButaca;
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
    public int getIdButaca() {
        return idButaca;
    }

    public void setIdButaca(int idbutaca) {
        this.idButaca = idButaca;
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
