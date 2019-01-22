package com.academiamoviles.d20183.apprestaurantes.cliente.ui.contenido;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.academiamoviles.d20183.apprestaurantes.cliente.R;
import com.academiamoviles.d20183.apprestaurantes.cliente.api.ApiClient;
import com.academiamoviles.d20183.apprestaurantes.cliente.model.PlatoModel;
import com.academiamoviles.d20183.apprestaurantes.cliente.ui.dialog.OrdenarDialog;
import com.academiamoviles.d20183.apprestaurantes.cliente.util.Globales;
import com.academiamoviles.d20183.apprestaurantes.cliente.util.MainUtil;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallePlatoActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private PlatoModel platoSeleccionado;

    @BindView(R.id.tvTitulo) TextView tvTitulo;
    @BindView(R.id.ivImagenPlato) ImageView ivImagenPlato;
    @BindView(R.id.tvNombrePlato) TextView tvNombrePlato;
    @BindView(R.id.tvPrecio) TextView tvPrecio;
    @BindView(R.id.tvDescripcion) TextView tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_plato);
        ButterKnife.bind(this);

        tvTitulo.setText(Globales.categoria.getCategoria());
        cargarDetallePlato();
    }

    private void cargarDetallePlato() {
        mostrarLoader();

        ApiClient.getApiRestService().obtenerDetallePlato(getIntent().getStringExtra("idPlato"))
                .enqueue(new Callback<PlatoModel>() {
                    @Override
                    public void onResponse(Call<PlatoModel> call, Response<PlatoModel> response) {
                        cerrarLoader();
                        if (response.isSuccessful()) {
                            platoSeleccionado = response.body();
                            llenarCamposUI();
                        } else {
                            mostrarErrorDeRed();
                        }
                    }

                    @Override
                    public void onFailure(Call<PlatoModel> call, Throwable t) {
                        cerrarLoader();
                        mostrarErrorDeRed();
                    }
                });
    }

    @OnClick(R.id.btnOrdenar)
    void ordenar() {
        MainUtil.openDialog(new OrdenarDialog(this, platoSeleccionado));
    }

    private void llenarCamposUI() {
        Glide.with(this).load(platoSeleccionado.getImagen()).into(ivImagenPlato);
        tvNombrePlato.setText(platoSeleccionado.getNombre_plato());
        tvPrecio.setText(String.valueOf(platoSeleccionado.getPrecio()));
        tvDescripcion.setText(platoSeleccionado.getDescripcion());
    }

    private void mostrarLoader() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.obteniendo_detalles));
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
        Toast.makeText(this, "Error de red, No se pudo obtener el detalle del plato", Toast.LENGTH_LONG).show();
        finish();
    }
}
