package com.example.application.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.security.ServicioAutenticacion;
import com.example.application.security.Rol;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Usuario")
@Route(value = "usuario", layout = DisposicionPrincipal.class)
public class VistaUsuario extends VerticalLayout {

    @Autowired
    public VistaUsuario(ServicioAutenticacion servicioAutenticacion) {
        Rol rol = servicioAutenticacion.obtenerUsuarioActual().getRol();
        if (rol == Rol.INVITADO) {
            UI.getCurrent().navigate("");
            add(new H2("Acceso denegado. Redirigiendo..."));
            return;
        }

        add(new H2("Panel de Usuario"));
        add(new H2("Usuario: " + servicioAutenticacion.obtenerUsuarioActual().getNombreUsuario()));
        add(new H2("Rol: USUARIO"));

        add(new H2("Tus permisos en el sistema:"));
        add(new Paragraph("✓ Leer datos - Solo tus propios datos"));
        add(new Paragraph("✓ Escribir en recursos - Según el servicio que uses"));
        
        add(new H2("Restricciones:"));
        add(new Paragraph("✗ Crear usuarios - No"));
        add(new Paragraph("✗ Modificar configuración - No"));
        add(new Paragraph("✗ Acceso temporal especial - No"));
        add(new Paragraph("✗ Ver logs de auditoría - No"));
    }
}
