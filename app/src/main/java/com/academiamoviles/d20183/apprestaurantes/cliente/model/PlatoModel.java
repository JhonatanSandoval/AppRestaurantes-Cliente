package com.academiamoviles.d20183.apprestaurantes.cliente.model;

public class PlatoModel {

    private String id_plato;
    private String id_categoria;
    private String nombre_plato;
    private String descripcion;
    private double precio;
    private String imagen;

    public PlatoModel() {
    }

    public PlatoModel(String id_plato, String id_categoria, String nombre_plato, String descripcion, double precio, String imagen) {
        this.id_plato = id_plato;
        this.id_categoria = id_categoria;
        this.nombre_plato = nombre_plato;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getId_plato() {
        return id_plato;
    }

    public void setId_plato(String id_plato) {
        this.id_plato = id_plato;
    }

    public String getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_plato() {
        return nombre_plato;
    }

    public void setNombre_plato(String nombre_plato) {
        this.nombre_plato = nombre_plato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
