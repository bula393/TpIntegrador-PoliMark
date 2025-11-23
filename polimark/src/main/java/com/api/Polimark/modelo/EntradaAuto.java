package com.api.Polimark.modelo;
import jakarta.persistence.*;

@Entity
@Table(name = "entradaAuto")
public class EntradaAuto {

    @Id
    @Column(name = "entradaArticuloIdArticulo") // Nombre exacto de la BD
    private Integer entradaArticuloIdArticulo;

    @OneToOne
    @MapsId // ← Esto hace que el ID se tome de la relación
    @JoinColumn(
            name = "entradaArticuloIdArticulo", // Mismo nombre que el @Id
            referencedColumnName = "articuloIdArticulo"
    )
    private Entrada entrada;

    @Column(nullable = false, length = 7)
    private String patente;

    @Column(name = "cantidadAuto")
    private Integer cantidadAuto;

    // 🔹 Constructores CORREGIDOS
    public EntradaAuto() {
    }

    // Constructor sin el ID explícito (deja que @MapsId lo maneje)
    public EntradaAuto(Entrada entrada, String patente, Integer cantidadAuto) {
        this.entrada = entrada;
        this.patente = patente;
        this.cantidadAuto = cantidadAuto;
    }

    // 🔹 Getters y Setters
    public Integer getEntradaArticuloIdArticulo() {
        return entradaArticuloIdArticulo;
    }

    public void setEntradaArticuloIdArticulo(Integer entradaArticuloIdArticulo) {
        this.entradaArticuloIdArticulo = entradaArticuloIdArticulo;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
        // Si estableces la entrada, actualiza el ID automáticamente
        if (entrada != null && entrada.getArticulo() != null) {
            this.entradaArticuloIdArticulo = entrada.getArticulo().getIdArticulo();
        }
    }

    // Resto de getters y setters...
}