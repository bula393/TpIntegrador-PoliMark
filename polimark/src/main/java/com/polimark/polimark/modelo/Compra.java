package com.polimark.Polimark.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompra")
    private int idcompra;

    @Column(name = "pagado")
    private Boolean pagado;

    // Relación con MetodoPago
    @ManyToOne
    @JoinColumn(name = "metodoPago_idmetodoPago", referencedColumnName = "idmetodoPago")
    private MetodoPago metodoPago;

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "Usuario_identificador", referencedColumnName = "identificador")
    private Usuario usuario;

    // Constructores
    public Compra() {}

    public Compra(Boolean pagado, MetodoPago metodoPago, Usuario usuario) {
        this.pagado = pagado;
        this.metodoPago = metodoPago;
        this.usuario = usuario;
    }

    // Getters y setters
    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
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
                "idcompra=" + idcompra +
                ", pagado=" + pagado +
                ", metodoPago=" + (metodoPago != null ? metodoPago.getIdmetodoPago() : null) +
                ", usuario=" + (usuario != null ? usuario.getIdentificador() : null) +
                '}';
    }
}

