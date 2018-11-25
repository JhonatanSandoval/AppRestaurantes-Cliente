package com.academiamoviles.d20183.apprestaurantes.cliente.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.academiamoviles.d20183.apprestaurantes.cliente.R;
import com.academiamoviles.d20183.apprestaurantes.cliente.adapter.CategoriasAdapter;
import com.academiamoviles.d20183.apprestaurantes.cliente.model.CategoriaModel;
import com.academiamoviles.d20183.apprestaurantes.cliente.util.CategoriasClickListener;

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
        adapter.setClickListener(new CategoriasClickListener() {
            @Override
            public void categoriaClick(String idCategoria) {
                abrirPantallaPrincipal(idCategoria);
            }
        });

        rvCategorias.setAdapter(adapter);

    }

    private void abrirPantallaPrincipal(String idCategoria) {
        Intent iPrincipal = new Intent(MenuActivity.this, PrincipalActivity.class);
        iPrincipal.putExtra("id_categoria", idCategoria);
        startActivity(iPrincipal);
        finish();
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
