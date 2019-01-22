package com.academiamoviles.d20183.apprestaurantes.cliente.model;

import com.google.gson.annotations.SerializedName;

public class CategoriaModel {

    @SerializedName("_id")
    private String id_categoria;

    @SerializedName("nombre")
    private String categoria;

    @SerializedName("descripcion")
    private String descripcion;

    public CategoriaModel() {
    }

    public CategoriaModel(String id_categoria, String categoria) {
        this.id_categoria = id_categoria;
        this.categoria = categoria;
    }

    public String getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
