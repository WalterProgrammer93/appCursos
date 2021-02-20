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

    private final String TABLA_CURSOS = "cursos";
    private static final String COL_CURSO_ID = "curso_id";
    private static final int NUM_COL_CURSO_ID = 0;
    private static final String COL_NOMBRE_CURSO = "nombre_curso";
    private static final int NUM_COL_NOMBRE_CURSO = 1;
    private static final String COL_CENTRO = "centro";
    private static final int NUM_COL_CENTRO = 2;
    private static final String COL_DISPONIBILIDAD = "disponibilidad";
    private static final int NUM_COL_DISPONIBILIDAD = 3;
    private static final String COL_NUM_ALUMNOS = "numero_alumnos";
    private static final int NUM_COL_NUM_ALUMNOS = 4;
    private static final String COL_MODOS = "modos";
    private static final int NUM_COL_MODOS = 5;

    private SQLiteDatabase bd;
    private AdminBD abd;

    public CursoBD(Context context) {
        abd = new AdminBD(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void leerBD() {
        bd = abd.getReadableDatabase();
    }
    public void escribirBD() {
        bd = abd.getWritableDatabase();
    }
    public void cerrarBD() {
        bd.close();
    }

    public SQLiteDatabase getBD() {
        return bd;
    }

    public void insertarCurso(Curso curso) {
        bd = abd.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(COL_NOMBRE_CURSO, curso.getNombreCurso());
        registro.put(COL_CENTRO, curso.getCentro());
        registro.put(COL_DISPONIBILIDAD, curso.getDisponibilidad());
        registro.put(COL_NUM_ALUMNOS, curso.getNumeroAlumnos());
        registro.put(COL_MODOS, curso.getModos());
        bd.insert(TABLA_CURSOS, null, registro);
        bd.close();
    }

    public int editarCurso(String nombre, Curso curso) {
        bd = abd.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(COL_NOMBRE_CURSO, curso.getNombreCurso());
        registro.put(COL_CENTRO, curso.getCentro());
        registro.put(COL_DISPONIBILIDAD, curso.getDisponibilidad());
        registro.put(COL_NUM_ALUMNOS, curso.getNumeroAlumnos());
        registro.put(COL_MODOS, curso.getModos());
        int edit = bd.update(TABLA_CURSOS, registro, COL_NOMBRE_CURSO + "=" + nombre,null);
        bd.close();
        return edit;
    }

    public int eliminarCurso(String nombre) {
        bd = abd.getWritableDatabase();
        int res = bd.delete(TABLA_CURSOS, COL_NOMBRE_CURSO + "=" + nombre, null);
        bd.close();
        return res;
    }

    public Curso buscarCurso(String nombre) {
        bd = abd.getReadableDatabase();
        Cursor cursor = bd.query(TABLA_CURSOS, new String[] {COL_CURSO_ID, COL_NOMBRE_CURSO, COL_CENTRO, COL_DISPONIBILIDAD, COL_NUM_ALUMNOS, COL_MODOS}, COL_NOMBRE_CURSO
                + " LIKE \"" + nombre + "\"", null, null, null, COL_NOMBRE_CURSO);
        bd.close();
        return seleccionarCurso(cursor);
    }

    public Curso seleccionarCurso(Cursor cursor) {
        bd = abd.getReadableDatabase();
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        Curso curso = new Curso();
        curso.setCursoId(cursor.getInt(NUM_COL_CURSO_ID));
        curso.setNombreCurso(cursor.getString(NUM_COL_NOMBRE_CURSO));
        curso.setCentro(cursor.getString(NUM_COL_CENTRO));
        curso.setDisponibilidad(cursor.getString(NUM_COL_DISPONIBILIDAD));
        curso.setNumeroAlumnos(cursor.getString(NUM_COL_NUM_ALUMNOS));
        curso.setModos(cursor.getString(NUM_COL_MODOS));
        cursor.close();
        bd.close();
        return curso;
    }
    public ArrayList<Curso> listarCursos() {
        bd = abd.getReadableDatabase();
        Cursor cursor = bd.query(TABLA_CURSOS, new String[] {
                COL_NOMBRE_CURSO, COL_DISPONIBILIDAD, COL_MODOS
        }, null, null, null, null, COL_NOMBRE_CURSO);

        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }
        ArrayList<Curso> listaCurso = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                Curso curso = new Curso();
                curso.setNombreCurso(cursor.getString(NUM_COL_NOMBRE_CURSO));
                curso.setDisponibilidad(cursor.getString(NUM_COL_DISPONIBILIDAD));
                curso.setModos(cursor.getString(NUM_COL_MODOS));
                listaCurso.add(curso);
            }
        }
        cursor.close();
        bd.close();
        return listaCurso;
    }

    public boolean isCursoExists(String nombreCurso, String centroCurso) {
        // array of columns to fetch
        String[] columns = {COL_NOMBRE_CURSO, COL_CENTRO};

        //Selection
        String selection = COL_NOMBRE_CURSO + " = ? and " + COL_CENTRO + " = ? ";

        //Selection Args
        String[] selection_Args = {nombreCurso, centroCurso};

        bd = abd.getReadableDatabase();
        //Query
        Cursor cursor = bd.query(TABLA_CURSOS,
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
