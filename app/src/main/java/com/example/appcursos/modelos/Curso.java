package com.example.appcursos.modelos;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.example.appcursos.bd.AdminBD;

import java.util.ArrayList;

public class Curso {

    private int cursoId;
    private String nombreCurso;
    private String centro;
    private String disponibilidad;
    private String numeroAlumnos;
    private String temas;
    private Bitmap iconoCurso;
    private int i;


    public Curso() {
    }

    public Curso(String nombreCurso, String centroCurso, ArrayList<String> listaDisponibilidad, String numeroAlumnos, ArrayList<String> listaTema) {
        this.nombreCurso = nombreCurso;
        this.centro = centroCurso;
        for (i = 0; i < listaDisponibilidad.size(); i++) {
            this.disponibilidad = listaDisponibilidad.get(i);
        }
        this.numeroAlumnos = numeroAlumnos;
        for (i = 0; i < listaTema.size(); i++) {
            this.temas = listaTema.get(i);
        }
    }

    public Bitmap getIconoCurso() {
        return iconoCurso;
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
