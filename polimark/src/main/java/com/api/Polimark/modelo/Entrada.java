package com.api.Polimark.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "entrada")
public class Entrada {

    private static int precioBasa = 13200;

    @Id
    @Column(name = "articuloIdArticulo")
    private int idArticulo;

    @OneToOne
    @MapsId  // ✅ Esto hace que el ID se tome de la relación
    @JoinColumn(name = "articuloIdArticulo", referencedColumnName = "idArticulo")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "compraIdCompra")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "funcionIdFuncion")
    private Funcion funcion;

    @ManyToOne
    @JoinColumn(name = "butacaIdButaca")
    private Butaca butaca;

    // ✅ Constructores
    public Entrada() {}

    public Entrada( Articulo articulo, Compra compra, Funcion funcion, Butaca butaca) {
        this.articulo = articulo;
        this.compra = compra;
        this.funcion = funcion;
        this.butaca = butaca;
    }

    public static int getPrecioBasa() {
        return precioBasa;
    }

    public static void setPrecioBasa(int precioBasa) {
        Entrada.precioBasa = precioBasa;
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

