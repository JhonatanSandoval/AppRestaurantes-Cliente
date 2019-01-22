package com.academiamoviles.d20183.apprestaurantes.cliente.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.academiamoviles.d20183.apprestaurantes.cliente.R;
import com.academiamoviles.d20183.apprestaurantes.cliente.model.PlatoModel;
import com.academiamoviles.d20183.apprestaurantes.cliente.util.ListaPlatoClickListener;

import java.util.ArrayList;
import java.util.List;

public class PlatosAdapter extends RecyclerView.Adapter<PlatosAdapter.PlatosHolder> {

    private List<PlatoModel> platos = new ArrayList<>();
    private ListaPlatoClickListener clickListener;

    public void setPlatos(List<PlatoModel> platos) {
        this.platos = platos;
        notifyDataSetChanged();
    }

    @Override
    public PlatosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celda_plato, parent, false);
        PlatosHolder platosHolder = new PlatosHolder(view);
        return platosHolder;
    }

    @Override
    public void onBindViewHolder(PlatosHolder holder, int position) {
        PlatoModel objPlato = platos.get(position);

        holder.tvNombrePlato.setText(objPlato.getNombre_plato());
        holder.tvDescripcion.setText(objPlato.getDescripcion());
        holder.tvPrecio.setText( String.valueOf(objPlato.getPrecio()) );
    }

    public void setClickListener(ListaPlatoClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    class PlatosHolder extends RecyclerView.ViewHolder {

        RelativeLayout rlContenido;
        ImageView ivPlato;
        TextView tvNombrePlato;
        TextView tvDescripcion;
        TextView tvPrecio;

        public PlatosHolder(View itemView) {
            super(itemView);

            rlContenido = itemView.findViewById(R.id.rlContenido);
            ivPlato = itemView.findViewById(R.id.ivPlato);
            tvNombrePlato = itemView.findViewById(R.id.tvNombrePlato);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);

        }


    }

}
