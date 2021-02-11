package com.example.appcursos.modelos;

import android.graphics.Bitmap;

public class Permiso {

    private int permisoId;
    private Usuario usuario;
    private Rol rol;
    private Bitmap iconoPermiso;

    public Permiso() {
    }

    public Permiso(Usuario usuario, Rol rol) {
        this.usuario = usuario;
        this.rol = rol;
    }

    public int getPermisoId() {
        return permisoId;
    }

    public void setPermisoId(int permisoId) {
        this.permisoId = permisoId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Bitmap getIconoPermiso() {
        return iconoPermiso;
    }

    public void setIconoPermiso(Bitmap iconoPermiso) {
        this.iconoPermiso = iconoPermiso;
    }
}
