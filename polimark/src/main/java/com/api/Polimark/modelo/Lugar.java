package com.api.Polimark.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Lugar")
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLugar")
    private Integer idLugar;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "direccion", length = 100)
    private String direccion;

    // Relación bidireccional con Sala (opcional)
    @OneToMany(mappedBy = "lugar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sala> salas;

    public Lugar() {}

    public Lugar(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Getters y setters
    public Integer getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(Integer idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }
}
