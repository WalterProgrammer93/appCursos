package com.example.appcursos.modelos;

public class Profesor {

    private int profesorId;
    private String nombreProfesor;
    private String apellidosProfesor;
    private String departamento;
    private String telefono_profesor;
    private Asignatura asignatura;

    public Profesor(String nombreProfesor, String apellidosProfesor, String departamento, String telefono_profesor, Asignatura asignatura) {
        this.nombreProfesor = nombreProfesor;
        this.apellidosProfesor = apellidosProfesor;
        this.departamento = departamento;
        this.telefono_profesor = telefono_profesor;
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

    public String getTelefono_profesor() {
        return telefono_profesor;
    }

    public void setTelefono_profesor(String telefono_profesor) {
        this.telefono_profesor = telefono_profesor;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
}
