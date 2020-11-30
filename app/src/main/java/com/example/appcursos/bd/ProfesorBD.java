package com.example.appcursos.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appcursos.modelos.Profesor;

import java.util.ArrayList;

public class ProfesorBD {

    private static final String DATABASE_NAME = "appcursos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLA_PROFESORES = "profesores";
    private static final String COL_PROFESOR_ID = "profesor_id";
    private static final int NUM_COL_PROFESOR_ID = 0;
    private static final String COL_NOMBRE_PROFESOR = "nombre_profesor";
    private static final int NUM_COL_NOMBRE_PROFESOR = 1;
    private static final String COL_APELLIDOS_PROFESOR = "apellidos_profesor";
    private static final int NUM_COL_APELLIDOS_PROFESOR = 2;
    private static final String COL_DEPARTAMENTO = "departamento";
    private static final int NUM_COL_DEPARTAMENTO = 3;
    private static final String COL_TELEFONO_PROFESOR = "telefono_profesor";
    private static final int NUM_COL_TELEFONO_PROFESOR = 4;
    private static final String COL_ASIGNATURA_ID = "asignatura_id";
    private static final int NUM_COL_ASIGNATURA_ID = 5;

    SQLiteDatabase bd;
    AdminBD abd;

    public ProfesorBD(Context context) {
        abd = new AdminBD(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertarProfesor(Profesor profesor) {
        abd.escribirBD();
        ContentValues registro = new ContentValues();
        registro.put(COL_NOMBRE_PROFESOR, profesor.getNombreProfesor());
        registro.put(COL_APELLIDOS_PROFESOR, profesor.getApellidosProfesor());
        registro.put(COL_DEPARTAMENTO, profesor.getDepartamento());
        registro.put(COL_TELEFONO_PROFESOR, profesor.getTelefonoProfesor());
        registro.put(COL_ASIGNATURA_ID, profesor.getAsignatura().getAsignaturaId());
        bd.insert(TABLA_PROFESORES, null, registro);
        abd.cerrarBD();
    }

    public void editarProfesor(int id, Profesor profesor) {
        abd.escribirBD();
        ContentValues registro = new ContentValues();
        registro.put(COL_NOMBRE_PROFESOR, profesor.getNombreProfesor());
        registro.put(COL_APELLIDOS_PROFESOR, profesor.getApellidosProfesor());
        registro.put(COL_DEPARTAMENTO, profesor.getDepartamento());
        registro.put(COL_TELEFONO_PROFESOR, profesor.getTelefonoProfesor());
        registro.put(COL_ASIGNATURA_ID, profesor.getAsignatura().getAsignaturaId());
        bd.update(TABLA_PROFESORES, registro, COL_ASIGNATURA_ID + "=" + id,null);
        abd.cerrarBD();
    }

    public void eliminarProfesor(String nombre) {
        abd.leerBD();
        bd.delete(TABLA_PROFESORES, COL_NOMBRE_PROFESOR + "=" + nombre, null);
        abd.cerrarBD();
    }

    public Profesor buscarProfesor(String nombre) {
        abd.leerBD();
        Cursor cursor = bd.query(TABLA_PROFESORES, new String[] {COL_PROFESOR_ID, COL_NOMBRE_PROFESOR, COL_APELLIDOS_PROFESOR, COL_DEPARTAMENTO, COL_TELEFONO_PROFESOR, COL_ASIGNATURA_ID}, COL_NOMBRE_PROFESOR
                + " LIKE \"" + nombre + "\"", null, null, null, null, COL_NOMBRE_PROFESOR);
        abd.cerrarBD();
        return seleccionarProfesor(cursor);
    }

    public Profesor seleccionarProfesor(Cursor cursor) {
        abd.leerBD();
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        Profesor profesor = new Profesor();
        profesor.setProfesorId(cursor.getInt(NUM_COL_PROFESOR_ID));
        profesor.setNombreProfesor(cursor.getString(NUM_COL_NOMBRE_PROFESOR));
        profesor.setApellidosProfesor(cursor.getString(NUM_COL_APELLIDOS_PROFESOR));
        profesor.setDepartamento(cursor.getString(NUM_COL_DEPARTAMENTO));
        profesor.setTelefonoProfesor(cursor.getString(NUM_COL_TELEFONO_PROFESOR));
        cursor.close();
        abd.cerrarBD();
        return profesor;
    }
    public ArrayList<Profesor> listarProfesor() {
        abd.leerBD();
        Cursor cursor = bd.query(TABLA_PROFESORES, new String[] {
                COL_PROFESOR_ID, COL_NOMBRE_PROFESOR, COL_APELLIDOS_PROFESOR, COL_DEPARTAMENTO, COL_TELEFONO_PROFESOR, COL_ASIGNATURA_ID
        }, null, null, null, null, null, COL_NOMBRE_PROFESOR);

        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        ArrayList<Profesor> listaProfesor = new ArrayList<>();
        while (cursor.moveToNext()) {
            Profesor profesor = new Profesor();
            profesor.setProfesorId(cursor.getInt(NUM_COL_PROFESOR_ID));
            profesor.setNombreProfesor(cursor.getString(NUM_COL_NOMBRE_PROFESOR));
            profesor.setApellidosProfesor(cursor.getString(NUM_COL_APELLIDOS_PROFESOR));
            profesor.setDepartamento(cursor.getString(NUM_COL_DEPARTAMENTO));
            profesor.setTelefonoProfesor(cursor.getString(NUM_COL_TELEFONO_PROFESOR));
            listaProfesor.add(profesor);
        }
        cursor.close();
        abd.cerrarBD();
        return listaProfesor;
    }
}
