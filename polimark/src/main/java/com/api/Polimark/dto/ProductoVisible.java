package com.api.Polimark.dto;

public class ProductoVisible {
    private Integer idArticulo;
    private String nombre;
    private String descripcion;
    private String categoria;
    private Integer precio;
    private Integer cantidad;
    private Integer stock;

    public ProductoVisible(Integer idArticulo, String nombre, String descripcion, String categoria, Integer precio, Integer cantidad, Integer stock) {
        this.idArticulo = idArticulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.stock = stock;
    }

    // Getters y Setters
    public Integer getIdArticulo() { return idArticulo; }
    public void setIdArticulo(Integer idArticulo) { this.idArticulo = idArticulo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public Integer getPrecio() { return precio; }
    public void setPrecio(Integer precio) { this.precio = precio; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}