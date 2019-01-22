package com.academiamoviles.d20183.apprestaurantes.cliente.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.academiamoviles.d20183.apprestaurantes.cliente.db.DBHelper;
import com.academiamoviles.d20183.apprestaurantes.cliente.model.PlatoModel;

import java.util.ArrayList;
import java.util.List;

public class PlatoDAO {

    private static final String TABLE_NAME = "platos";

    private static final String COLUMNA_ID = "_id";
    private static final String COLUMNA_NOMBRE = "nombre";
    private static final String COLUMNA_DESCRIPCION = "descripcion";
    private static final String COLUMNA_CATEGORIA_ID = "categoria_id";
    private static final String COLUMNA_PRECIO = "precio";
    private static final String COLUMNA_IMAGEN = "imagen";
    private static final String COLUMNA_CANTIDAD_ORDEN = "cantidad";

    public static final String CREATE_TABLE_QUERY = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            COLUMNA_ID + " TEXT , " +
            COLUMNA_NOMBRE + " TEXT , " +
            COLUMNA_DESCRIPCION + " TEXT ," +
            COLUMNA_CATEGORIA_ID + " TEXT " +
            COLUMNA_PRECIO + " TEXT , " +
            COLUMNA_IMAGEN + " TEXT , " +
            COLUMNA_CANTIDAD_ORDEN + " INTEGER ) ";

    private DBHelper dbHelper;

    public PlatoDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertarPlato(PlatoModel plato) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNA_ID, plato.getId_plato());
        contentValues.put(COLUMNA_CATEGORIA_ID, plato.getId_categoria());
        contentValues.put(COLUMNA_NOMBRE, plato.getNombre_plato());
        contentValues.put(COLUMNA_DESCRIPCION, plato.getDescripcion());
        contentValues.put(COLUMNA_PRECIO, String.valueOf(plato.getPrecio()));
        contentValues.put(COLUMNA_IMAGEN, plato.getImagen());
        contentValues.put(COLUMNA_CANTIDAD_ORDEN, plato.getCantidadOrden());

        dbHelper.getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }

    public void insertarPlatos(List<PlatoModel> platos) {
        for (PlatoModel item : platos) {
            insertarPlato(item);
        }
    }

    public void aumentarCantidadOrdenPlato(String idPlato, int cantidad) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNA_CANTIDAD_ORDEN, String.valueOf(cantidad));

        dbHelper.getWritableDatabase()
                .update(TABLE_NAME, contentValues, COLUMNA_ID + "=?", new String[]{idPlato});
    }

    public PlatoModel obtenerPlatoPorId(String idPlato) {
        Cursor cursor = dbHelper.getReadableDatabase()
                .query(TABLE_NAME, null, COLUMNA_ID + "=?", new String[]{idPlato},
                        null, null, null);
        return obtenerPlatoDeCursor(cursor);
    }

    public List<PlatoModel> obtenerPlatosDeCategoria(String idCategoria) {
        List<PlatoModel> platos = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase()
                .query(TABLE_NAME, null, COLUMNA_CATEGORIA_ID + "=?", new String[]{idCategoria},
                        null, null, null, null);
        while (cursor.moveToNext()) {
            platos.add(obtenerPlatoDeCursor(cursor));
        }
        return platos;
    }

    public void resetCantidadOrdenPlatos(List<PlatoModel> platos) {
        for (PlatoModel item : platos) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMNA_CANTIDAD_ORDEN, 0);
            dbHelper.getWritableDatabase()
                    .update(TABLE_NAME, contentValues, COLUMNA_ID + "=?", new String[]{item.getId_plato()});
        }
    }

    public List<PlatoModel> obtenerPlatosParaOrden() {
        List<PlatoModel> platos = new ArrayList<>();
        Cursor cursor = dbHelper.getReadableDatabase()
                .query(TABLE_NAME, null, COLUMNA_CANTIDAD_ORDEN + " > 1", null,
                        null, null, null, null);
        while (cursor.moveToNext()) {
            platos.add(obtenerPlatoDeCursor(cursor));
        }
        return platos;
    }

    public void eliminarPlatosDeCategoria(String idCategoria) {
        dbHelper.getWritableDatabase()
                .delete(TABLE_NAME, COLUMNA_CATEGORIA_ID + "=?", new String[]{idCategoria});
    }

    public PlatoModel obtenerPlatoDeCursor(Cursor cursor) {
        PlatoModel plato = null;
        if (cursor != null) {
            plato = new PlatoModel();
            plato.setId_plato(cursor.getColumnName(cursor.getColumnIndex(COLUMNA_ID)));
            plato.setNombre_plato(cursor.getColumnName(cursor.getColumnIndex(COLUMNA_NOMBRE)));
            plato.setDescripcion(cursor.getColumnName(cursor.getColumnIndex(COLUMNA_DESCRIPCION)));
            plato.setId_categoria(cursor.getColumnName(cursor.getColumnIndex(COLUMNA_CATEGORIA_ID)));
            plato.setPrecio(Double.parseDouble(cursor.getColumnName(cursor.getColumnIndex(COLUMNA_PRECIO))));
            plato.setImagen(cursor.getColumnName(cursor.getColumnIndex(COLUMNA_IMAGEN)));
        }
        return plato;
    }
}
