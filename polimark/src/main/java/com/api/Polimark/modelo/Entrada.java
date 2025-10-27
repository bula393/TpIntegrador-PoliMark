package com.api.Polimark.modelo;

import jakarta.persistence.*;

@Entity
public class Entrada {

    @Id
    @Column(name = "articulo_idarticulo")
    private int idArticulo;

    @OneToOne
    @JoinColumn(name = "articulo_idarticulo", referencedColumnName = "idarticulo", insertable = false, updatable = false)
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "compra_idcompra")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "funcion_idfuncion")
    private Funcion funcion;

    @ManyToOne
    @JoinColumn(name = "butaca_idbutaca")
    private Butaca butaca;

    // ✅ Constructores
    public Entrada() {}

    public Entrada(int idArticulo, Articulo articulo, Compra compra, Funcion funcion, Butaca butaca) {
        this.idArticulo = idArticulo;
        this.articulo = articulo;
        this.compra = compra;
        this.funcion = funcion;
        this.butaca = butaca;
    }

    // ✅ Getters y Setters
    public int getIdArticulo() { return idArticulo; }
    public void setIdArticulo(int idArticulo) { this.idArticulo = idArticulo; }

    public Articulo getArticulo() { return articulo; }
    public void setArticulo(Articulo articulo) { this.articulo = articulo; }

    public Compra getCompra() { return compra; }
    public void setCompra(Compra compra) { this.compra = compra; }

    public Funcion getFuncion() { return funcion; }
    public void setFuncion(Funcion funcion) { this.funcion = funcion; }

    public Butaca getButaca() { return butaca; }
    public void setButaca(Butaca butaca) { this.butaca = butaca; }
}

