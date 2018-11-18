package com.academiamoviles.d20183.apprestaurantes.cliente.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.academiamoviles.d20183.apprestaurantes.cliente.R;
import com.academiamoviles.d20183.apprestaurantes.cliente.adapter.CategoriasAdapter;
import com.academiamoviles.d20183.apprestaurantes.cliente.model.CategoriaModel;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView rvCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        rvCategorias = findViewById(R.id.rvCategorias);
        rvCategorias.setLayoutManager(new LinearLayoutManager(MenuActivity.this));

        CategoriasAdapter adapter = new CategoriasAdapter();
        adapter.setCategorias(obtenerCategorias());

        rvCategorias.setAdapter(adapter);

    }

    private List<CategoriaModel> obtenerCategorias() {
        List<CategoriaModel> categorias = new ArrayList<>();

        categorias.add(new CategoriaModel("1", "Entradas"));
        categorias.add(new CategoriaModel("1", "Segundos"));
        categorias.add(new CategoriaModel("1", "Sopas"));
        categorias.add(new CategoriaModel("1", "Postres"));
        categorias.add(new CategoriaModel("1", "Vinos"));

        return categorias;
    }

}
