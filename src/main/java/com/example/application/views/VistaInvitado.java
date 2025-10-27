package com.example.application.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.security.Rol;
import com.example.application.security.ServicioAutenticacion;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Invitado")
@Route(value = "invitado", layout = DisposicionPrincipal.class)
public class VistaInvitado extends VerticalLayout {

    @Autowired
    public VistaInvitado(ServicioAutenticacion servicioAutenticacion) {
        Rol rol = servicioAutenticacion.obtenerUsuarioActual().getRol();
        if (rol != Rol.ADMIN) {
            UI.getCurrent().navigate("");
            add(new H2("Acceso denegado. Redirigiendo..."));
            return;
        }
        
        add(new H2("Gestión de Invitados (Solo Admin)"));
        add(new H2("Usuario: " + servicioAutenticacion.obtenerUsuarioActual().getNombreUsuario()));
        add(new H2("Rol: ADMINISTRADOR"));
        
        add(new H2("Información sobre el rol INVITADO:"));
        add(new Paragraph("Los invitados tienen acceso MUY limitado al sistema."));
        
        add(new H2("Permisos del rol INVITADO:"));
        add(new Paragraph("✓ Leer datos - Solo públicos"));
        add(new Paragraph("✓ Acceso temporal - Sí (limitado)"));
        
        add(new H2("Restricciones del INVITADO:"));
        add(new Paragraph("✗ Crear usuarios - No"));
        add(new Paragraph("✗ Modificar configuración - No"));
        add(new Paragraph("✗ Escribir en recursos críticos - No"));
        add(new Paragraph("✗ Ver logs de auditoría - No"));
    }
}
