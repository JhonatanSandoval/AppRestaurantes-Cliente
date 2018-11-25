package com.academiamoviles.d20183.apprestaurantes.cliente.ui.contenido;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.academiamoviles.d20183.apprestaurantes.cliente.R;
import com.academiamoviles.d20183.apprestaurantes.cliente.adapter.PlatosAdapter;
import com.academiamoviles.d20183.apprestaurantes.cliente.model.PlatoModel;

import java.util.ArrayList;
import java.util.List;

public class ListaPlatosFragment extends Fragment {

    private View view;

    private LinearLayout llContenidoNoLogeado;
    private LinearLayout llContenidoLogeado;

    private RecyclerView rvPlatos;
    private PlatosAdapter platosAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista_platos, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        enlazarElementos();
        validarUsuarioLogeado();
        cargarListaPlatos();
    }

    private void cargarListaPlatos() {
        platosAdapter = new PlatosAdapter();
        platosAdapter.setPlatos( obtenerListaPlatos() );

        rvPlatos.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPlatos.setAdapter(platosAdapter);
    }

    private List<PlatoModel> obtenerListaPlatos() {
        List<PlatoModel> platos = new ArrayList<>();
        platos.add(
                new PlatoModel("HU768ADS768",
                        "89AS87DAHSD",
                        "Lomo Saltado",
                        "Con chicha",
                        13.00,
                        "")
        );
        platos.add(
                new PlatoModel("HU768ADS768",
                        "89AS87DAHSD",
                        "Ají de Gallina",
                        "Buenazo",
                        10.50,
                        "")
        );
        platos.add(
                new PlatoModel("HU768ADS768",
                        "89AS87DAHSD",
                        "Cevichito mixto",
                        "Arrrto aji",
                        15.00,
                        "")
        );

        return platos;
    }

    private void validarUsuarioLogeado() {
        boolean logeado = true;
        if (logeado) {
            llContenidoLogeado.setVisibility(View.VISIBLE);
            llContenidoNoLogeado.setVisibility(View.GONE);
        } else {
            llContenidoLogeado.setVisibility(View.GONE);
            llContenidoNoLogeado.setVisibility(View.VISIBLE);
        }
    }

    private void enlazarElementos() {
        llContenidoNoLogeado = view.findViewById(R.id.llContenidoNoLogeado);
        llContenidoLogeado = view.findViewById(R.id.llContenidoLogeado);

        rvPlatos = view.findViewById(R.id.rvPlatos);
    }


}
