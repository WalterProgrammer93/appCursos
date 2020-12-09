package com.example.appcursos.modelos;
import android.database.Cursor;
import android.graphics.Bitmap;

import com.example.appcursos.bd.AdminBD;

public class Asignatura {

    private int asignaturaId;
    private String nombreAsignatura;
    private String descripcionAsignatura;
    private Curso curso;
    private Bitmap iconoAsignatura;
    AdminBD abd;

    public Asignatura() {
    }

    public Asignatura(String nombreAsignatura, String descripcionAsignatura, Curso curso) {
        this.nombreAsignatura = nombreAsignatura;
        this.descripcionAsignatura = descripcionAsignatura;
        this.curso = curso;
    }

    public Bitmap getIconoAsignatura() {
        return iconoAsignatura;
    }

    public int getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(int asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getDescripcionAsignatura() {
        return descripcionAsignatura;
    }

    public void setDescripcionAsignatura(String descripcionAsignatura) {
        this.descripcionAsignatura = descripcionAsignatura;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        String query = "select curso_id as curso from cursos where curso_id = " + curso.getCursoId();
        Cursor cursor = abd.getReadableDatabase().rawQuery(query,null);
        while(cursor.moveToNext()) {
            int fila = Integer.valueOf(cursor.getString(cursor.getColumnIndex("curso_id")));
        }
        cursor.close();
        this.curso = curso;
    }
}
