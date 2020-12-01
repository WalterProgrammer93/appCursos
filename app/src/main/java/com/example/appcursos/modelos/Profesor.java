package com.example.appcursos.modelos;

import android.graphics.Bitmap;

public class Profesor {

    private int profesorId;
    private String nombreProfesor;
    private String apellidosProfesor;
    private String departamento;
    private String telefonoProfesor;
    private Asignatura asignatura;
    private Bitmap iconoProfesor;

    public Profesor() {
    }

    public Profesor(String nombreProfesor, String apellidosProfesor, String departamento, String telefonoProfesor, Asignatura asignatura) {
        this.nombreProfesor = nombreProfesor;
        this.apellidosProfesor = apellidosProfesor;
        this.departamento = departamento;
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

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
}
