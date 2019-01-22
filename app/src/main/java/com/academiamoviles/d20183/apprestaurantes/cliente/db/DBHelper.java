package com.academiamoviles.d20183.apprestaurantes.cliente.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.academiamoviles.d20183.apprestaurantes.cliente.db.dao.CategoriaDAO;
import com.academiamoviles.d20183.apprestaurantes.cliente.util.Globales;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, Globales.DATABASE_NAME, null, Globales.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoriaDAO.CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
