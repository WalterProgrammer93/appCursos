package com.example.appcursos.modelos;

public class Profesor {

    private int profesorId;
    private String nombreProfesor;
    private String apellidosProfesor;
    private String departamento;
    private String telefono;
    private Asignatura asignatura;

    public Profesor(String nombreProfesor, String apellidosProfesor, String departamento, String telefono, Asignatura asignatura) {
        this.nombreProfesor = nombreProfesor;
        this.apellidosProfesor = apellidosProfesor;
        this.departamento = departamento;
        this.telefono = telefono;
        this.asignatura = asignatura;
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
