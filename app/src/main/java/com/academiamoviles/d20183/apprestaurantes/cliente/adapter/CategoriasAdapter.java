package com.academiamoviles.d20183.apprestaurantes.cliente.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.academiamoviles.d20183.apprestaurantes.cliente.R;
import com.academiamoviles.d20183.apprestaurantes.cliente.model.CategoriaModel;

import java.util.ArrayList;
import java.util.List;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.CategoriaHolder> {

    private List<CategoriaModel> categorias = new ArrayList<>();

    @Override
    public CategoriaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CategoriaHolder holder = new CategoriaHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celda_categoria, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(CategoriaHolder holder, int position) {
        CategoriaModel objCategoria = categorias.get(position);
        holder.tvCategoria.setText(objCategoria.getCategoria());
    }

    public void setCategorias(List<CategoriaModel> lista) {
        categorias = lista;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    class CategoriaHolder extends RecyclerView.ViewHolder {

        TextView tvCategoria;

        public CategoriaHolder(View itemView) {
            super(itemView);

            tvCategoria = itemView.findViewById(R.id.tvCategoria);

        }
    }

}
