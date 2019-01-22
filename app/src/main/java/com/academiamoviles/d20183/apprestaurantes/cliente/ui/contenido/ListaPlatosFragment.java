package com.academiamoviles.d20183.apprestaurantes.cliente.ui.contenido;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.Toast;

import com.academiamoviles.d20183.apprestaurantes.cliente.R;
import com.academiamoviles.d20183.apprestaurantes.cliente.adapter.PlatosAdapter;
import com.academiamoviles.d20183.apprestaurantes.cliente.api.ApiClient;
import com.academiamoviles.d20183.apprestaurantes.cliente.db.dao.PlatoDAO;
import com.academiamoviles.d20183.apprestaurantes.cliente.model.PlatoModel;
import com.academiamoviles.d20183.apprestaurantes.cliente.sp.AppPrefs;
import com.academiamoviles.d20183.apprestaurantes.cliente.util.Globales;
import com.academiamoviles.d20183.apprestaurantes.cliente.util.ListaPlatoClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaPlatosFragment extends Fragment implements ListaPlatoClickListener {

    private View view;

    private LinearLayout llContenidoNoLogeado;
    private LinearLayout llContenidoLogeado;

    private RecyclerView rvPlatos;
    private PlatosAdapter platosAdapter;

    private AppPrefs appPrefs;
    private ProgressDialog progressDialog;
    private PlatoDAO platoDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista_platos, container, false);
        appPrefs = new AppPrefs(getActivity());
        platoDAO = new PlatoDAO(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        enlazarElementos();
        validarUsuarioLogeado();
        configurarRecyclerView();
    }

    private void configurarRecyclerView() {
        platosAdapter = new PlatosAdapter();
        platosAdapter.setClickListener(this);

        rvPlatos.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPlatos.setAdapter(platosAdapter);

        cargarListaPlatos();
    }

    private void cargarListaPlatos() {
        mostrarLoader();

        ApiClient.getApiRestService().obtenerPlatosDeCategoria(Globales.categoria.getId_categoria()).enqueue(new Callback<List<PlatoModel>>() {
            @Override
            public void onResponse(Call<List<PlatoModel>> call, Response<List<PlatoModel>> response) {
                cerrarLoader();
                if (response.isSuccessful()) {
                    List<PlatoModel> platos = response.body();
                    if (platos != null && !platos.isEmpty()) {
                        platosAdapter.setPlatos(platos);

                        platoDAO.eliminarPlatosDeCategoria(Globales.categoria.getId_categoria());
                        platoDAO.insertarPlatos(platos);
                    }
                } else {
                    mostrarErrorDeRed();
                }
            }

            @Override
            public void onFailure(Call<List<PlatoModel>> call, Throwable t) {
                cerrarLoader();
                mostrarErrorDeRed();
            }
        });
    }

    private void validarUsuarioLogeado() {
        if (appPrefs.isLogeado()) {
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

    private void mostrarLoader() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getString(R.string.obteniendo_platos));
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
        Toast.makeText(getActivity(), "Error de red", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickPlato(String idPlato) {
        Intent iDetallePlato = new Intent(getActivity(), DetallePlatoActivity.class);
        iDetallePlato.putExtra("idPlato", idPlato);
        getActivity().startActivity(iDetallePlato);
    }
}
