package com.example.appcursos.modelos;

import android.graphics.Bitmap;

public class Asignatura {

    private int asignaturaId;
    private String nombreAsignatura;
    private String descripcionAsignatura;
    private String curso;
    private Bitmap iconoAsignatura;

    public Asignatura() {
    }

    public Asignatura(String nombreAsignatura, String descripcionAsignatura, String curso) {
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return nombreAsignatura;
    }
}
