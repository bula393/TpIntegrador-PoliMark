package com.api.Polimark.modelo;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "compraHasPromocion")
public class CompraHasPromocion implements Serializable {

    @EmbeddedId
    private CompraHasPromocionId id;

    @Column(name = "cantidadAplicada")
    private Integer cantidad;

    @ManyToOne
    @MapsId("compraIdCompra")
    @JoinColumn(name = "compraIdCompra", referencedColumnName = "idcompra")
    private Compra compra;

    @ManyToOne
    @MapsId("promocionesIdPromociones")
    @JoinColumn(name = "promocionesIdPromociones", referencedColumnName = "idPromociones")
    private Promocion promociones;

    // 🔹 Constructor vacío
    public CompraHasPromocion() {}

    // 🔹 Constructor con todos los campos
    public CompraHasPromocion(Compra compra, Promocion promociones,int cantidad) {
        this.compra = compra;
        this.cantidad = cantidad;
        this.promociones = promociones;
        this.id = new CompraHasPromocionId(compra.getIdCompra(), promociones.getIdPromocion());
    }

    // 🔹 Getters y Setters
    public CompraHasPromocionId getId() {
        return id;
    }

    public void setId(CompraHasPromocionId id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Promocion getPromociones() {
        return promociones;
    }

    public void setPromociones(Promocion promociones) {
        this.promociones = promociones;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
