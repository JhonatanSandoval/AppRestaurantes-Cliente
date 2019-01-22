package com.academiamoviles.d20183.apprestaurantes.cliente.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.academiamoviles.d20183.apprestaurantes.cliente.R;
import com.academiamoviles.d20183.apprestaurantes.cliente.db.dao.PlatoDAO;
import com.academiamoviles.d20183.apprestaurantes.cliente.model.PlatoModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrdenarDialog extends Dialog {

    private Context context;
    private PlatoModel plato;

    private int cantidad = 1;
    @BindView(R.id.tvCantidad) TextView tvCantidad;

    private PlatoDAO platoDAO;

    public OrdenarDialog(@NonNull Context context, PlatoModel platoSeleccionado) {
        super(context);
        this.context = context;
        this.plato = platoSeleccionado;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ordenar);
        ButterKnife.bind(this);

        platoDAO = new PlatoDAO(getContext());

        setUpWidth();
    }

    @OnClick(R.id.btnOrdenar)
    void confirmarOrden() {
        platoDAO.aumentarCantidadOrdenPlato(plato.getId_plato(), cantidad);
        Toast.makeText(getContext(), "Agregado correctamente a la Orden", Toast.LENGTH_LONG).show();
        OrdenarDialog.this.dismiss();
    }

    @OnClick(R.id.tvAumentar)
    void aumentar() {
        cantidad += 1;
        tvCantidad.setText(String.valueOf(cantidad));
    }

    @OnClick(R.id.tvReducir)
    void reducir() {
        if (cantidad == 1) {
            tvCantidad.setText("1");
        } else {
            cantidad -= 1;
            tvCantidad.setText(String.valueOf(cantidad));
        }
    }

    private void setUpWidth() {
        if (getWindow().getAttributes() != null) {
            ViewGroup.LayoutParams params = getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        }
    }
}
