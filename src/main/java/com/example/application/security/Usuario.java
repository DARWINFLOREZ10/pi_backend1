package com.example.application.security;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String nombreUsuario;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, Rol rol) {
        this.nombreUsuario = nombreUsuario;
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
