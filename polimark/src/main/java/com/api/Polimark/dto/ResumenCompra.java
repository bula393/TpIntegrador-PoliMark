package com.api.Polimark.dto;

import com.api.Polimark.modelo.Articulo;
import com.api.Polimark.modelo.Butaca;

import java.util.List;

public class ResumenCompra {
    private List<ButacaVisible> butacasVisibles;
    private List<ArticuloVisible> articuloVisibles;
    private FuncionVisible funcionVisibles;
    private Integer total;

    public ResumenCompra(List<ButacaVisible> butacasVisibles,Integer total, List<ArticuloVisible> articuloVisibles, FuncionVisible funcionVisibles) {
        this.butacasVisibles = butacasVisibles;
        this.articuloVisibles = articuloVisibles;
        this.funcionVisibles = funcionVisibles;
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<ButacaVisible> getButacasVisibles() {
        return butacasVisibles;
    }

    public void setButacasVisibles(List<ButacaVisible> butacasVisibles) {
        this.butacasVisibles = butacasVisibles;
    }

    public List<ArticuloVisible> getArticuloVisibles() {
        return articuloVisibles;
    }

    public void setArticuloVisibles(List<ArticuloVisible> articuloVisibles) {
        this.articuloVisibles = articuloVisibles;
    }

    public FuncionVisible getFuncionVisibles() {
        return funcionVisibles;
    }

    public void setFuncionVisibles(FuncionVisible funcionVisibles) {
        this.funcionVisibles = funcionVisibles;
    }
}
