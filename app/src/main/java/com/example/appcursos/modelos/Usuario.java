package com.example.appcursos.modelos;

import android.graphics.Bitmap;

public class Usuario {

    private int usuarioId;
    private String username;
    private String email;
    private String password;
    private Bitmap iconoUsuario;

    public Usuario() {
    }

    public Usuario(int usuarioId, String username, String email, String password) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Usuario(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Bitmap getIconoUsuario() {
        return iconoUsuario;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
