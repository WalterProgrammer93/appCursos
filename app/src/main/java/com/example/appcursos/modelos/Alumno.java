package com.example.appcursos.modelos;

public class Alumno {

    private int alumnoId;
    private String nombreAlumno;
    private String apellidosAlumno;
    private String dni;
    private String telefono_alumno;
    private Asignatura asignatura;

    public Alumno(String nombreAlumno, String apellidosAlumno, String dni, String telefono_alumno, Asignatura asignatura) {
        this.nombreAlumno = nombreAlumno;
        this.apellidosAlumno = apellidosAlumno;
        this.dni = dni;
        this.telefono_alumno = telefono_alumno;
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

    public String getTelefonoAlumno() {
        return telefono_alumno;
    }

    public void setTelefonoAlumno(String telefono_alumno) {
        this.telefono_alumno = telefono_alumno;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
}
