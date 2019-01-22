package com.academiamoviles.d20183.apprestaurantes.cliente.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.academiamoviles.d20183.apprestaurantes.cliente.R;
import com.academiamoviles.d20183.apprestaurantes.cliente.adapter.CategoriasAdapter;
import com.academiamoviles.d20183.apprestaurantes.cliente.api.ApiClient;
import com.academiamoviles.d20183.apprestaurantes.cliente.db.dao.CategoriaDAO;
import com.academiamoviles.d20183.apprestaurantes.cliente.model.CategoriaModel;
import com.academiamoviles.d20183.apprestaurantes.cliente.util.CategoriasClickListener;
import com.academiamoviles.d20183.apprestaurantes.cliente.util.Globales;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView rvCategorias;
    private CategoriasAdapter adapter;

    private ProgressDialog progressDialog;

    private CategoriaDAO categoriaDAO;
    private List<CategoriaModel> categorias = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        categoriaDAO = new CategoriaDAO(this);

        rvCategorias = findViewById(R.id.rvCategorias);
        rvCategorias.setLayoutManager(new LinearLayoutManager(MenuActivity.this));

        adapter = new CategoriasAdapter();
        adapter.setClickListener(new CategoriasClickListener() {
            @Override
            public void categoriaClick(CategoriaModel categoriaModel) {
                Globales.categoria = categoriaModel;
                abrirPantallaPrincipal();
            }
        });
        rvCategorias.setAdapter(adapter);

        cargarCategorias();

    }

    private void abrirPantallaPrincipal() {
        Intent iPrincipal = new Intent(MenuActivity.this, PrincipalActivity.class);
        startActivity(iPrincipal);
        finish();
    }

    private void cargarCategorias() {
        categorias = categoriaDAO.obtenerCategorias();

        mostrarLoader();

        ApiClient.getApiRestService().obtenerCategorias().enqueue(new Callback<List<CategoriaModel>>() {
            @Override
            public void onResponse(Call<List<CategoriaModel>> call, Response<List<CategoriaModel>> response) {
                cerrarLoader();
                if (response.isSuccessful()) {
                    categorias = response.body();
                    if (categorias != null && !categorias.isEmpty()) {
                        adapter.setCategorias(categorias);

                        categoriaDAO.eliminarCategorias();
                        categoriaDAO.insertarCategorias(categorias);
                    }

                } else {
                    mostrarErrorDeRed();
                    adapter.setCategorias(categorias);

                }
            }

            @Override
            public void onFailure(Call<List<CategoriaModel>> call, Throwable t) {
                cerrarLoader();
                mostrarErrorDeRed();
                adapter.setCategorias(categorias);
            }
        });
    }

    private void mostrarLoader() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.obteniendo_categorias));
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void cerrarLoader() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    private void mostrarErrorDeRed() {
        Toast.makeText(this, "Error de red", Toast.LENGTH_SHORT).show();
    }

}
