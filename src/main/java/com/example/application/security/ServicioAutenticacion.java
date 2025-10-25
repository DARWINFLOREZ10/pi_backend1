package com.example.application.security;

import org.springframework.stereotype.Service;

import com.vaadin.flow.server.VaadinSession;

@Service
public class ServicioAutenticacion {

    public Usuario obtenerUsuarioActual() {
        VaadinSession session = VaadinSession.getCurrent();
        if (session == null) {
            return new Usuario("default", Rol.USUARIO);
        }
        Usuario u = session.getAttribute(Usuario.class);
        if (u == null) {
            u = new Usuario("usuario_predeterminado", Rol.USUARIO);
            session.setAttribute(Usuario.class, u);
        }
        return u;
    }

    public void establecerUsuarioActual(Usuario usuario) {
        VaadinSession session = VaadinSession.getCurrent();
        if (session != null) {
            session.setAttribute(Usuario.class, usuario);
        }
    }

    public void establecerRol(Rol rol) {
        Usuario u = obtenerUsuarioActual();
        u.setRol(rol);
        establecerUsuarioActual(u);
    }

    public void cerrarSesion() {
        VaadinSession session = VaadinSession.getCurrent();
        if (session != null) {
            session.setAttribute(Usuario.class, null);
        }
    }
}
