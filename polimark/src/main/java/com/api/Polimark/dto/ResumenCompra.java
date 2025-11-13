package com.api.Polimark.dto;

import java.util.List;

public class ResumenCompra {
    private List<ButacaVisible> butacasVisibles;
    private List<EntradaVisible> entradasVisibles;
    private List<ProductoVisible> productosVisibles;
    private FuncionVisible funcionVisible;
    private Integer total;

    public ResumenCompra(List<ButacaVisible> butacasVisibles, List<EntradaVisible> entradasVisibles,
                         List<ProductoVisible> productosVisibles, FuncionVisible funcionVisible, Integer total) {
        this.butacasVisibles = butacasVisibles;
        this.entradasVisibles = entradasVisibles;
        this.productosVisibles = productosVisibles;
        this.funcionVisible = funcionVisible;
        this.total = total;
    }

    // Getters y Setters
    public List<ButacaVisible> getButacasVisibles() { return butacasVisibles; }
    public void setButacasVisibles(List<ButacaVisible> butacasVisibles) { this.butacasVisibles = butacasVisibles; }

    public List<EntradaVisible> getEntradasVisibles() { return entradasVisibles; }
    public void setEntradasVisibles(List<EntradaVisible> entradasVisibles) { this.entradasVisibles = entradasVisibles; }

    public List<ProductoVisible> getProductosVisibles() { return productosVisibles; }
    public void setProductosVisibles(List<ProductoVisible> productosVisibles) { this.productosVisibles = productosVisibles; }

    public FuncionVisible getFuncionVisible() { return funcionVisible; }
    public void setFuncionVisible(FuncionVisible funcionVisible) { this.funcionVisible = funcionVisible; }

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }
}