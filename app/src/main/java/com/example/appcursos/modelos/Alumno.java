package com.example.appcursos.modelos;

import android.graphics.Bitmap;

public class Alumno {

    private int alumnoId;
    private String nombreAlumno;
    private String apellidosAlumno;
    private String dni;
    private String telefonoAlumno;
    private String asignatura;
    private Bitmap iconoAlumno;

    public Alumno() {
    }

    public Alumno(String nombreAlumno, String apellidosAlumno, String dni, String telefonoAlumno, String asignatura) {
        this.nombreAlumno = nombreAlumno;
        this.apellidosAlumno = apellidosAlumno;
        this.dni = dni;
        this.telefonoAlumno = telefonoAlumno;
        this.asignatura = asignatura;
    }

    public Bitmap getIconoAlumno() {
        return iconoAlumno;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidosAlumno() {
        return apellidosAlumno;
    }

    public void setApellidosAlumno(String apellidosAlumno) {
        this.apellidosAlumno = apellidosAlumno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefonoAlumno() {
        return telefonoAlumno;
    }

    public void setTelefonoAlumno(String telefonoAlumno) {
        this.telefonoAlumno = telefonoAlumno;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
}
