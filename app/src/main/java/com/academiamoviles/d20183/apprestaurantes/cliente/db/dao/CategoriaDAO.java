package com.academiamoviles.d20183.apprestaurantes.cliente.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.academiamoviles.d20183.apprestaurantes.cliente.db.DBHelper;
import com.academiamoviles.d20183.apprestaurantes.cliente.model.CategoriaModel;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private static final String TABLE_NAME = "categorias";

    private static final String COLUMNA_ID = "_id";
    private static final String COLUMNA_NOMBRE = "nombre";
    private static final String COLUMNA_DESCRIPCION = "descripcion";

    public static final String CREATE_TABLE_QUERY = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            COLUMNA_ID + " TEXT , " +
            COLUMNA_NOMBRE + " TEXT , " +
            COLUMNA_DESCRIPCION + " TEXT ) ";

    private DBHelper dbHelper;

    public CategoriaDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertarCategoria(CategoriaModel categoria) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNA_ID, categoria.getId_categoria());
        contentValues.put(COLUMNA_NOMBRE, categoria.getCategoria());
        contentValues.put(COLUMNA_DESCRIPCION, categoria.getDescripcion());
        database.insert(TABLE_NAME, null, contentValues);
    }

    public List<CategoriaModel> obtenerCategorias() {
        List<CategoriaModel> categorias = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase()
                .query(TABLE_NAME, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            categorias.add(obtenerCategoriaDeCursor(cursor));
        }
        return categorias;
    }

    public CategoriaModel obtenerCategoriaDeCursor(Cursor cursor) {
        CategoriaModel categoria = null;
        if (cursor != null) {
            categoria = new CategoriaModel();
            categoria.setId_categoria(cursor.getColumnName(cursor.getColumnIndex(COLUMNA_ID)));
            categoria.setCategoria(cursor.getColumnName(cursor.getColumnIndex(COLUMNA_NOMBRE)));
            categoria.setDescripcion(cursor.getColumnName(cursor.getColumnIndex(COLUMNA_DESCRIPCION)));
        }
        return categoria;
    }

    public void insertarCategorias(List<CategoriaModel> categorias) {
        for (CategoriaModel item : categorias) {
            insertarCategoria(item);
        }
    }

    public void eliminarCategorias() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(TABLE_NAME, null, null);
    }

}
