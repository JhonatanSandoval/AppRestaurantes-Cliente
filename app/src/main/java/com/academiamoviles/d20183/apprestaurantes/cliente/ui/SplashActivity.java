package com.academiamoviles.d20183.apprestaurantes.cliente.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.academiamoviles.d20183.apprestaurantes.cliente.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        iniciarTemporizador();

    }

    private void iniciarTemporizador() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirMenuActivity();
            }
        }, 1000 * 1); // TODO: cambiar a "2 segundos"
    }

    private void abrirMenuActivity() {
        Intent iMenu = new Intent(SplashActivity.this, MenuActivity.class);
        startActivity(iMenu);
        finish();
    }

}
