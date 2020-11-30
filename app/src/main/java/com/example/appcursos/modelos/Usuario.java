package com.example.appcursos.modelos;

public class Usuario {

    private int usuarioId;
    private String username;
    private String email;
    private String rol;
    private String password;

    public Usuario() {
    }

    public Usuario(int usuarioId, String username, String email, String rol, String password) {
        this.usuarioId = usuarioId;
        this.username = username;
        this.email = email;
        this.rol = rol;
        this.password = password;
    }

    public Usuario(String username, String email, String rol, String password) {
        this.username = username;
        this.email = email;
        this.rol = rol;
        this.password = password;
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
