package com.api.Polimark.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identificador")
    private int identificador;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "mail")
    private String mail;

    @Column(name = "contrasenaHash") // El nombre tiene paréntesis, así que va entre backticks
    private byte[] contrasenaHash;

    @Column(name = "puntos")
    private Integer puntos;

    // Relación con Rango
    @ManyToOne
    @JoinColumn(name = "rangoIdRango", referencedColumnName = "idRango")
    private Rango rango;

    // Constructores
    public Usuario(int identificador, String nombre, String apellido, byte[] bytes, String mail) {}

    public Usuario(int identificador,String nombre, String apellido, String mail, byte[] contrasenaHash) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.contrasenaHash = contrasenaHash;
    }

    public Usuario() {

    }

    // Getters y Setters
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public byte[] getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(byte[] contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public Rango getRango() {
        return rango;
    }

    public void setRango(Rango rango) {
        this.rango = rango;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "identificador=" + identificador +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", contrasenaHash='" + contrasenaHash + '\'' +
                ", rango=" + (rango != null ? rango.getIdRango() : null) +
                ", puntos=" + puntos +
                '}';
    }
}
