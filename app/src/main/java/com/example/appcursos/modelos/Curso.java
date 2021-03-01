package com.example.appcursos.modelos;

import java.util.ArrayList;

public class Curso {

    private int cursoId;
    private String nombreCurso;
    private String centro;
    private String disponibilidad;
    private String numeroAlumnos;
    private String modos;
    private int iconoCurso;

    public Curso() {
    }

    public Curso(String nombreCurso, String centroCurso, ArrayList<String> listaDisponibilidad, String numeroAlumnos, ArrayList<String> listaModos) {
        this.nombreCurso = nombreCurso;
        this.centro = centroCurso;
        int i;
        for (i = 0; i < listaDisponibilidad.size(); i++) {
            this.disponibilidad = listaDisponibilidad.get(i);
        }
        this.numeroAlumnos = numeroAlumnos;
        for (i = 0; i < listaModos.size(); i++) {
            this.modos = listaModos.get(i);
        }
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getNumeroAlumnos() {
        return numeroAlumnos;
    }

    public void setNumeroAlumnos(String numeroAlumnos) {
        this.numeroAlumnos = numeroAlumnos;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getModos() {
        return modos;
    }

    public void setModos(String modos) {
        this.modos = modos;
    }

    public int getIconoCurso() {
        return iconoCurso;
    }

    public void setIconoCurso(int iconoCurso) {
        this.iconoCurso = iconoCurso;
    }
}
