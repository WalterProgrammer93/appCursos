package com.example.appcursos.modelos;

import android.graphics.Bitmap;

public class Permiso {

    private int permisoId;
    private String usuario;
    private String rol;
    private Bitmap iconoPermiso;

    public Permiso() {
    }

    public Permiso(String usuario, String rol) {
        this.usuario = usuario;
        this.rol = rol;
    }

    public Permiso(int permisoId, String usuario, String rol) {
        this.permisoId = permisoId;
        this.usuario = usuario;
        this.rol = rol;
    }

    public int getPermisoId() {
        return permisoId;
    }

    public void setPermisoId(int permisoId) {
        this.permisoId = permisoId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Bitmap getIconoPermiso() {
        return iconoPermiso;
    }

    public void setIconoPermiso(Bitmap iconoPermiso) {
        this.iconoPermiso = iconoPermiso;
    }



}
