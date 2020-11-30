package com.example.appcursos.modelos;

public class Curso {

    private int cursoId;
    private String nombreCurso;
    private String centro;
    private String numeroAlumnos;
    private String disponibilidad;
    private String temas;
    private int i;

    public Curso() {
    }

    public Curso(String nombreCurso, String centro, String numeroAlumnos, String disponibilidad, String temas) {
        this.nombreCurso = nombreCurso;
        this.centro = centro;
        this.numeroAlumnos = numeroAlumnos;
        this.disponibilidad = disponibilidad;
        this.temas = temas;
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

    public String getTemas() {
        return temas;
    }

    public void setTemas(String temas) {
        this.temas = temas;
    }
}
