package com.api.Polimark.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompra")
    private int idCompra;

    @Column(name = "pagado")
    private Boolean pagado;

    @Column(name = "monto")
    private int montopagado;

    @Column(name = "fecha")
    private LocalDate fecha;


    // Relación con MetodoPago
    @ManyToOne
    @JoinColumn(name = "metodoPagoIdMetodoPago", referencedColumnName = "idMetodoPago")
    private MetodoPago metodoPago;

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "usuarioIdentificador", referencedColumnName = "identificador")
    private Usuario usuario;

    // Constructores
    public Compra() {}

    public Compra(Boolean pagado, MetodoPago metodoPago, Usuario usuario) {
        this.pagado = pagado;
        this.metodoPago = metodoPago;
        this.usuario = usuario;
    }

    public int getMontopagado() {
        return montopagado;
    }

    public void setMontopagado(int montopagado) {
        this.montopagado = montopagado;
    }

    // Getters y setters


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdcompra(int idcompra) {
        this.idCompra = idcompra;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "idcompra=" + idCompra +
                ", pagado=" + pagado +
                ", metodoPago=" + (metodoPago != null ? metodoPago.getIdmetodoPago() : null) +
                ", usuario=" + (usuario != null ? usuario.getIdentificador() : null) +
                '}';
    }
}

