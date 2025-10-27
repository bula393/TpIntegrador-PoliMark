package com.api.Polimark.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "articuloHasPromocion")
public class PromocionHasArticulo implements Serializable {

    @EmbeddedId
    private PromocionArticuloId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPromocion")
    @JoinColumn(name = "promociones_idpromociones")
    private Promocion promocion;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idArticulo")
    @JoinColumn(name = "articulo_idarticulo")
    private Articulo articulo;

    // Constructores
    public PromocionHasArticulo() {
        this.id = new PromocionArticuloId();
    }

    public PromocionHasArticulo(Promocion promocion, Articulo articulo) {
        this.promocion = promocion;
        this.articulo = articulo;
        this.id = new PromocionArticuloId(promocion.getIdPromocion(), articulo.getIdArticulo());
    }

    // Getters y Setters
    public PromocionArticuloId getId() {
        return id;
    }

    public void setId(PromocionArticuloId id) {
        this.id = id;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PromocionHasArticulo)) return false;
        PromocionHasArticulo that = (PromocionHasArticulo) o;
        return Objects.equals(promocion, that.promocion) &&
                Objects.equals(articulo, that.articulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promocion, articulo);
    }

    @Override
    public String toString() {
        return "PromocionHasArticulo{" +
                "promocion=" + (promocion != null ? promocion.getIdPromocion() : "null") +
                ", articulo=" + (articulo != null ? articulo.getIdArticulo() : "null") +
                '}';
    }
}