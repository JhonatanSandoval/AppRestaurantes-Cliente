package com.academiamoviles.d20183.apprestaurantes.cliente.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.academiamoviles.d20183.apprestaurantes.cliente.R;
import com.academiamoviles.d20183.apprestaurantes.cliente.adapter.ContenidoAdapter;
import com.academiamoviles.d20183.apprestaurantes.cliente.ui.contenido.CheffFragment;
import com.academiamoviles.d20183.apprestaurantes.cliente.ui.contenido.ListaPlatosFragment;
import com.academiamoviles.d20183.apprestaurantes.cliente.ui.contenido.OrdenFragment;

public class PrincipalActivity extends AppCompatActivity {

    private ViewPager vpContenido;
    private TabLayout tlOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        enlazarElementos();
        configurarContenido();
        configurarIconos();
    }

    private void configurarIconos() {
        tlOpciones.getTabAt(0).setIcon(R.mipmap.v1_tabico2);
        tlOpciones.getTabAt(1).setIcon(R.mipmap.v1_tabico3);
        tlOpciones.getTabAt(2).setIcon(R.mipmap.v1_tabico4);
    }

    private void configurarContenido() {
        ContenidoAdapter adapter = new ContenidoAdapter(getSupportFragmentManager());
        adapter.agregarPagina(new ListaPlatosFragment());
        adapter.agregarPagina(new OrdenFragment());
        adapter.agregarPagina(new CheffFragment());

        vpContenido.setAdapter(adapter);
        tlOpciones.setupWithViewPager(vpContenido);
    }

    private void enlazarElementos() {
        vpContenido = findViewById(R.id.vpContenido);
        tlOpciones = findViewById(R.id.tlOpciones);
    }

}
