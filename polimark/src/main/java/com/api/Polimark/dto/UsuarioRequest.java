package com.api.Polimark.dto;

import com.api.Polimark.modelo.Usuario;

public class UsuarioRequest {
    private int identificador;
    private String nombre;
    private String apellido;
    private String mail;
    private String contrasena; // Texto plano
    private Integer puntos;
    private Integer rangoId;

    public UsuarioRequest(Usuario usuario){
        identificador = usuario.getIdentificador();
        nombre = usuario.getNombre();
        apellido = usuario.getApellido();
        mail = usuario.getMail();
        puntos = usuario.getPuntos();
        rangoId = usuario.getRango().getIdRango();
    }

    public UsuarioRequest() {
    }

    // Constructor con parámetros (opcional)
    public UsuarioRequest(int identificador, String nombre, String apellido, String mail, String contrasena, int puntos, int rangoId) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.contrasena = contrasena;
        this.puntos = puntos;
        this.rangoId = rangoId;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Integer getPuntos() { return puntos; }
    public void setPuntos(Integer puntos) { this.puntos = puntos; }

    public Integer getRangoId() { return rangoId; }
    public void setRangoId(Integer rangoId) { this.rangoId = rangoId; }
}