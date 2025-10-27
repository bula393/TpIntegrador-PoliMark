package com.api.Polimark.modelo;


import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PromocionArticuloId implements Serializable {

    private Integer idPromocion;
    private Integer idArticulo;

    // Constructores
    public PromocionArticuloId() {}

    public PromocionArticuloId(Integer idPromocion, Integer idArticulo) {
        this.idPromocion = idPromocion;
        this.idArticulo = idArticulo;
    }

    // Getters y Setters
    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PromocionArticuloId)) return false;
        PromocionArticuloId that = (PromocionArticuloId) o;
        return Objects.equals(idPromocion, that.idPromocion) &&
                Objects.equals(idArticulo, that.idArticulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPromocion, idArticulo);
    }
}