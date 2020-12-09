package com.example.appcursos.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.appcursos.modelos.Asignatura;
import com.example.appcursos.modelos.Curso;

import java.util.ArrayList;

public class AsignaturaBD {

    private static final String DATABASE_NAME = "appcursos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLA_ASIGNATURAS = "asignaturas";
    private static final String COL_ASIGNATURA_ID = "asignatura_id";
    private static final int NUM_COL_ASIGNATURA_ID = 0;
    private static final String COL_NOMBRE_ASIGNATURA = "nombre_asignatura";
    private static final int NUM_COL_NOMBRE_ASIGNATURA = 1;
    private static final String COL_DESCRIPCION_ASIGNATURA = "descripcion_asignatura";
    private static final int NUM_COL_DESCRIPCION_ASIGNATURA = 2;
    private static final String COL_CURSO_ID = "curso_id";
    private static final int NUM_COL_CURSO_ID = 3;

    private SQLiteDatabase bd;
    private AdminBD abd;

    public AsignaturaBD(Context context) {
        abd = new AdminBD(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertarAsignatura(Asignatura asignatura) {
        bd = abd.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(COL_NOMBRE_ASIGNATURA, asignatura.getNombreAsignatura());
        registro.put(COL_DESCRIPCION_ASIGNATURA, asignatura.getDescripcionAsignatura());
        registro.put(COL_CURSO_ID, asignatura.getCurso().getCursoId());
        bd.insert(TABLA_ASIGNATURAS, null, registro);
        bd.close();
    }

    public void editarAsignatura(int id, Asignatura asignatura) {
        bd = abd.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(COL_NOMBRE_ASIGNATURA, asignatura.getNombreAsignatura());
        registro.put(COL_DESCRIPCION_ASIGNATURA, asignatura.getDescripcionAsignatura());
        registro.put(COL_CURSO_ID, asignatura.getCurso().getCursoId());
        bd.update(TABLA_ASIGNATURAS, registro, COL_ASIGNATURA_ID + "=" + id,null);
        bd.close();
    }

    public void eliminarAsignatura(String nombre) {
        bd = abd.getReadableDatabase();
        bd.delete(TABLA_ASIGNATURAS, COL_NOMBRE_ASIGNATURA + "=" + nombre, null);
        bd.close();
    }

    public Asignatura buscarAsignatura(String nombre) {
        bd = abd.getReadableDatabase();
        Cursor cursor = bd.query(TABLA_ASIGNATURAS, new String[] {COL_ASIGNATURA_ID, COL_NOMBRE_ASIGNATURA, COL_DESCRIPCION_ASIGNATURA}, COL_NOMBRE_ASIGNATURA
                + " LIKE \"" + nombre + "\"", null, null, null, null, COL_NOMBRE_ASIGNATURA);
        bd.close();
        return seleccionarAsignatura(cursor);
    }

    public Asignatura seleccionarAsignatura(Cursor cursor) {
        bd = abd.getReadableDatabase();
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        Asignatura asignatura = new Asignatura();
        asignatura.setAsignaturaId(cursor.getInt(NUM_COL_ASIGNATURA_ID));
        asignatura.setNombreAsignatura(cursor.getString(NUM_COL_NOMBRE_ASIGNATURA));
        asignatura.setDescripcionAsignatura(cursor.getString(NUM_COL_DESCRIPCION_ASIGNATURA));
        cursor.close();
        bd.close();
        return asignatura;
    }
    public ArrayList<Asignatura> listarAsignatura() {
        bd = abd.getReadableDatabase();
        Cursor cursor = bd.query(TABLA_ASIGNATURAS, new String[] {
                COL_ASIGNATURA_ID, COL_NOMBRE_ASIGNATURA, COL_DESCRIPCION_ASIGNATURA
        }, null, null, null, null, null, COL_NOMBRE_ASIGNATURA);

        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        ArrayList<Asignatura> listaAsignatura = new ArrayList<>();
        while (cursor.moveToNext()) {
            Asignatura asignatura = new Asignatura();
            asignatura.setAsignaturaId(cursor.getInt(NUM_COL_ASIGNATURA_ID));
            asignatura.setNombreAsignatura(cursor.getString(NUM_COL_NOMBRE_ASIGNATURA));
            asignatura.setDescripcionAsignatura(cursor.getString(NUM_COL_DESCRIPCION_ASIGNATURA));
            listaAsignatura.add(asignatura);
        }
        cursor.close();
        bd.close();
        return listaAsignatura;
    }

    public boolean isAsignaturaExists(String nombreAsignatura) {
        String[] columns = {COL_NOMBRE_ASIGNATURA};

        //Selection
        String selection = COL_NOMBRE_ASIGNATURA + " = ? ";

        //Selection Args
        String[] selection_Args = {nombreAsignatura};

        bd = abd.getReadableDatabase();
        //Query
        Cursor cursor = bd.query(TABLA_ASIGNATURAS,
                columns,
                selection,
                selection_Args,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            return true;
        }
        cursor.close();
        bd.close();
        return false;
    }
}
