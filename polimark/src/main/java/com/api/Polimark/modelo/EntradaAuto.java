package com.api.Polimark.modelo;


import jakarta.persistence.*;

@Entity
@Table(name = "entradaAuto")
public class EntradaAuto {

    @Id
    @Column(name = "entrada_articulo_idarticulo")
    private int entradaArticuloIdArticulo;

    @OneToOne
    @JoinColumn(
            name = "entrada_articulo_idarticulo",
            referencedColumnName = "articulo_idarticulo",
            insertable = false,
            updatable = false
    )
    private Entrada entrada;

    @Column(nullable = false, length = 7)
    private String patente;

    @Column(name = "cantidadAuto")
    private Integer cantidadAuto;

    // 🔹 Constructores
    public EntradaAuto() {
    }

    public EntradaAuto(int entradaArticuloIdArticulo, String patente, Integer cantidadAuto) {
        this.entradaArticuloIdArticulo = entradaArticuloIdArticulo;
        this.patente = patente;
        this.cantidadAuto = cantidadAuto;
    }

    // 🔹 Getters y Setters
    public int getEntradaArticuloIdArticulo() {
        return entradaArticuloIdArticulo;
    }

    public void setEntradaArticuloIdArticulo(int entradaArticuloIdArticulo) {
        this.entradaArticuloIdArticulo = entradaArticuloIdArticulo;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Integer getCantidadAuto() {
        return cantidadAuto;
    }

    public void setCantidadAuto(Integer cantidadAuto) {
        this.cantidadAuto = cantidadAuto;
    }

    @Override
    public String toString() {
        return "EntradaAuto{" +
                "entradaArticuloIdArticulo=" + entradaArticuloIdArticulo +
                ", patente='" + patente + '\'' +
                ", cantidadAuto=" + cantidadAuto +
                '}';
    }
}
