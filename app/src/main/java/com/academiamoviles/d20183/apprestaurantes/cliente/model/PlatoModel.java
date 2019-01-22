package com.academiamoviles.d20183.apprestaurantes.cliente.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class PlatoModel {

    @SerializedName("_id")
    private String id_plato;

    @SerializedName("categoria_id")
    private String id_categoria;

    @SerializedName("nombre")
    private String nombre_plato;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("precio")
    private double precio;

    @SerializedName("imagen")
    private String imagen;

    private int cantidadOrden = 0;

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

    public int getCantidadOrden() {
        return cantidadOrden;
    }

    public void setCantidadOrden(int cantidadOrden) {
        this.cantidadOrden = cantidadOrden;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
