package com.api.Polimark.modelo;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "promociones")
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpromociones")
    private Integer idPromocion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 45)
    private TipoPromo tipo;

    @Column(name = "caracteristicas", length = 45)
    private String caracteristicas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Rango_idRango", nullable = false)
    private Rango rango;

    // Constructores
    public Promocion() {}

    public Promocion(TipoPromo tipo, String caracteristicas, Rango rango) {
        this.tipo = tipo;
        this.caracteristicas = caracteristicas;
        this.rango = rango;
    }

    // Getters y Setters
    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public TipoPromo getTipo() {
        return tipo;
    }

    public void setTipo(TipoPromo tipo) {
        this.tipo = tipo;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Rango getRango() {
        return rango;
    }

    public void setRango(Rango rango) {
        this.rango = rango;
    }

    // Método para verificar si es una promoción de descuento
    public boolean esDescuento() {
        return tipo == TipoPromo.DESCUENTO;
    }

    public boolean esDosPorUno() {
        return tipo == TipoPromo.DOSXUNO;
    }

    public boolean esCombo() {
        return tipo == TipoPromo.COMBO;
    }

    @Override
    public String toString() {
        return "Promocion{" +
                "idPromocion=" + idPromocion +
                ", tipo=" + tipo +
                ", caracteristicas='" + caracteristicas + '\'' +
                ", rango=" + (rango != null ? rango.getNombre() : "null") +
                '}';
    }
}
