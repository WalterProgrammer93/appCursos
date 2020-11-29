package com.example.appcursos.modelos;

public class Alumno {

    private int alumnoId;
    private String nombreAlumno;
    private String apellidosAlumno;
    private String dni;
    private String genero;
    private String telefono;
    private Asignatura asignatura;

    public Alumno(String nombreAlumno, String apellidosAlumno, String dni, String genero, String telefono, Asignatura asignatura) {
        this.nombreAlumno = nombreAlumno;
        this.apellidosAlumno = apellidosAlumno;
        this.dni = dni;
        this.genero = genero;
        this.telefono = telefono;
        this.asignatura = asignatura;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
}
