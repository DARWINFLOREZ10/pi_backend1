package com.example.application.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.security.Rol;
import com.example.application.security.ServicioAutenticacion;
import com.example.application.security.Usuario;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Login")
@Route(value = "login", layout = DisposicionPrincipal.class)
public class VistaLogin extends VerticalLayout {

    @Autowired
    public VistaLogin(ServicioAutenticacion servicioAutenticacion) {
        add(new H2("Control de acceso - Selector de rol"));

        Select<Rol> selector = new Select<>();
        selector.setItems(Rol.values());
        selector.setValue(servicioAutenticacion.obtenerUsuarioActual().getRol());
        selector.setLabel("Rol");

        Button establecerRol = new Button("Usar rol seleccionado", e -> {
            Rol r = selector.getValue();
            servicioAutenticacion.establecerRol(r);
            UI.getCurrent().getPage().reload();
        });

        Button cerrarSesion = new Button("Cerrar sesión (reset)", e -> {
            servicioAutenticacion.cerrarSesion();
            UI.getCurrent().getPage().reload();
        });

        Usuario u = servicioAutenticacion.obtenerUsuarioActual();
        add(selector, establecerRol, cerrarSesion);
        add(new H2("Usuario actual: " + u.getNombreUsuario() + " (" + u.getRol() + ")"));
    }
}
