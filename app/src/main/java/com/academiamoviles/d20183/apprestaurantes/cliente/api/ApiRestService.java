package com.academiamoviles.d20183.apprestaurantes.cliente.api;

import com.academiamoviles.d20183.apprestaurantes.cliente.model.CategoriaModel;
import com.academiamoviles.d20183.apprestaurantes.cliente.model.PlatoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiRestService {

    @GET("api/categorias")
    Call<List<CategoriaModel>> obtenerCategorias();

    @GET("api/platos/categoria/{categoriaId}")
    Call<List<PlatoModel>> obtenerPlatosDeCategoria(@Path("categoriaId") String categoriaId);

    @GET("api/platos/{platoId}/show")
    Call<PlatoModel> obtenerDetallePlato(@Path("platoId") String platoId);

}
