package com.example.appcursos.modelos;

import android.graphics.Bitmap;

public class Rol {

    private int rolId;
    private String nombreRol;
    private String descripcionRol;
    private Bitmap iconoRol;

    public Rol() {
    }

    public Rol(String nombreRol, String descripcionRol) {
        this.nombreRol = nombreRol;
        this.descripcionRol = descripcionRol;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public Bitmap getIconoRol() {
        return iconoRol;
    }

    public void setIconoRol(Bitmap iconoRol) {
        this.iconoRol = iconoRol;
    }
}
