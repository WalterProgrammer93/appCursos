package com.example.appcursos.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.appcursos.modelos.Curso;
import java.util.ArrayList;

public class CursoBD {

    private static final String DATABASE_NAME = "appcursos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLA_CURSOS = "cursos";
    private static final String COL_CURSO_ID = "curso_id";
    private static final int NUM_COL_CURSO_ID = 0;
    private static final String COL_NOMBRE_CURSO = "nombre_curso";
    private static final int NUM_COL_NOMBRE_CURSO = 1;
    private static final String COL_CENTRO = "centro";
    private static final int NUM_COL_CENTRO = 2;
    private static final String COL_NUM_ALUMNOS = "numero_alumnos";
    private static final int NUM_COL_NUM_ALUMNOS = 3;
    private static final String COL_DISPONIBILIDAD = "disponibilidad";
    private static final int NUM_COL_DISPONIBILIDAD = 4;
    private static final String COL_TEMAS = "temas";
    private static final int NUM_COL_TEMAS = 5;

    SQLiteDatabase bd;
    AdminBD abd;

    public CursoBD(Context context) {
        abd = new AdminBD(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertarCurso(Curso curso) {
        abd.escribirBD();
        ContentValues registro = new ContentValues();
        registro.put(COL_NOMBRE_CURSO, curso.getNombreCurso());
        registro.put(COL_CENTRO, curso.getCentro());
        registro.put(COL_NUM_ALUMNOS, curso.getNumeroAlumnos());
        registro.put(COL_DISPONIBILIDAD, curso.getDisponibilidad());
        registro.put(COL_TEMAS, curso.getTemas());
        bd.insert(TABLA_CURSOS, null, registro);
        abd.cerrarBD();
    }

    public void editarCurso(int id, Curso curso) {
        abd.escribirBD();
        ContentValues registro = new ContentValues();
        registro.put(COL_NOMBRE_CURSO, curso.getNombreCurso());
        registro.put(COL_CENTRO, curso.getCentro());
        registro.put(COL_NUM_ALUMNOS, curso.getNumeroAlumnos());
        registro.put(COL_DISPONIBILIDAD, curso.getDisponibilidad());
        registro.put(COL_TEMAS, curso.getTemas());
        bd.update(TABLA_CURSOS, registro, COL_CURSO_ID + "=" + id,null);
        abd.cerrarBD();
    }

    public void eliminarCurso(String nombre) {
        abd.leerBD();
        bd.delete(TABLA_CURSOS, COL_NOMBRE_CURSO + "=" + nombre, null);
        abd.cerrarBD();
    }

    public Curso buscarCurso(String nombre) {
        abd.leerBD();
        Cursor cursor = bd.query(TABLA_CURSOS, new String[] {COL_CURSO_ID, COL_NOMBRE_CURSO, COL_CENTRO, COL_NUM_ALUMNOS, COL_DISPONIBILIDAD, COL_TEMAS}, COL_NOMBRE_CURSO
                + " LIKE \"" + nombre + "\"", null, null, null, null, COL_NOMBRE_CURSO);
        abd.cerrarBD();
        return seleccionarCurso(cursor);
    }

    public Curso seleccionarCurso(Cursor cursor) {
        abd.leerBD();
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        Curso curso = new Curso();
        curso.setCursoId(cursor.getInt(NUM_COL_CURSO_ID));
        curso.setNombreCurso(cursor.getString(NUM_COL_NOMBRE_CURSO));
        curso.setCentro(cursor.getString(NUM_COL_CENTRO));
        curso.setDisponibilidad(cursor.getString(NUM_COL_NUM_ALUMNOS));
        curso.setDisponibilidad(cursor.getString(NUM_COL_DISPONIBILIDAD));
        curso.setTemas(cursor.getString(NUM_COL_TEMAS));
        cursor.close();
        abd.cerrarBD();
        return curso;
    }
    public ArrayList<Curso> listarCurso() {
        abd.leerBD();
        Cursor cursor = bd.query(TABLA_CURSOS, new String[] {
                COL_CURSO_ID, COL_NOMBRE_CURSO, COL_CENTRO, COL_NUM_ALUMNOS, COL_DISPONIBILIDAD
        }, null, null, null, null, null, COL_NOMBRE_CURSO);

        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        ArrayList<Curso> listaCurso = new ArrayList<>();
        while (cursor.moveToNext()) {
            Curso curso = new Curso();
            curso.setCursoId(cursor.getInt(NUM_COL_CURSO_ID));
            curso.setNombreCurso(cursor.getString(NUM_COL_NOMBRE_CURSO));
            curso.setCentro(cursor.getString(NUM_COL_CENTRO));
            curso.setDisponibilidad(cursor.getString(NUM_COL_NUM_ALUMNOS));
            curso.setDisponibilidad(cursor.getString(NUM_COL_DISPONIBILIDAD));
            curso.setTemas(cursor.getString(NUM_COL_TEMAS));
            listaCurso.add(curso);
        }
        cursor.close();
        abd.cerrarBD();
        return listaCurso;
    }
}
