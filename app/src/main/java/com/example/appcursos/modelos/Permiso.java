package com.example.appcursos.modelos;

import android.graphics.Bitmap;

public class Permiso {

    private int permisoId;
    private int usuario;
    private int rol;
    private Bitmap iconoPermiso;

    public Permiso() {
    }

    public Permiso(int usuario, int rol) {
        this.usuario = usuario;
        this.rol = rol;
    }

    public Permiso(int permisoId, int usuario, int rol) {
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

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public Bitmap getIconoPermiso() {
        return iconoPermiso;
    }

    public void setIconoPermiso(Bitmap iconoPermiso) {
        this.iconoPermiso = iconoPermiso;
    }



}
