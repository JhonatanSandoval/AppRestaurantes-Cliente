package com.academiamoviles.d20183.apprestaurantes.cliente.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {

    private static final String PREFS_NAME = "AppRest.Clientes";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String KEY_LOGEADO = "logeado";

    public AppPrefs(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogeado(boolean valor) {
        editor.putBoolean(KEY_LOGEADO, valor);
        editor.commit();
    }

    public boolean isLogeado() {
        return sharedPreferences.getBoolean(KEY_LOGEADO, false);
    }

}
