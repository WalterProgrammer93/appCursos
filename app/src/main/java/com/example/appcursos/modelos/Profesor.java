package com.example.appcursos.modelos;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Profesor {

    private int profesorId;
    private String nombreProfesor;
    private String apellidosProfesor;
    private String departamento;
    private String telefonoProfesor;
    private String asignatura;
    private Bitmap iconoProfesor;

    public Profesor() {
    }

    public Profesor(String nombreProfesor, String apellidosProfesor, ArrayList<String> listaDepartamentos, String telefonoProfesor, String asignatura) {
        this.nombreProfesor = nombreProfesor;
        this.apellidosProfesor = apellidosProfesor;
        for (int i = 0; i < listaDepartamentos.size(); i++) {
            this.departamento = listaDepartamentos.get(i);
        }
        this.telefonoProfesor = telefonoProfesor;
        this.asignatura = asignatura;
    }

    public Bitmap getIconoProfesor() {
        return iconoProfesor;
    }

    public int getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(int profesorId) {
        this.profesorId = profesorId;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getApellidosProfesor() {
        return apellidosProfesor;
    }

    public void setApellidosProfesor(String apellidosProfesor) {
        this.apellidosProfesor = apellidosProfesor;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTelefonoProfesor() {
        return telefonoProfesor;
    }

    public void setTelefonoProfesor(String telefonoProfesor) {
        this.telefonoProfesor = telefonoProfesor;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
}
