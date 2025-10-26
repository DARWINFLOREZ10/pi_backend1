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

@PageTitle("Admin")
@Route(value = "admin", layout = DisposicionPrincipal.class)
public class VistaAdmin extends VerticalLayout {

    @Autowired
    public VistaAdmin(ServicioAutenticacion servicioAutenticacion) {
        if (servicioAutenticacion.obtenerUsuarioActual().getRol() != Rol.ADMIN) {
            UI.getCurrent().navigate("");
            add(new H2("Acceso denegado. Redirigiendo..."));
            return;
        }
        
        add(new H2("Panel de Administración"));
        add(new H2("Usuario: " + servicioAutenticacion.obtenerUsuarioActual().getNombreUsuario()));
        add(new H2("Rol: ADMINISTRADOR"));
        
        add(new H2("Tus permisos en el sistema:"));
        add(new Paragraph("✓ Crear usuarios - Sí"));
        add(new Paragraph("✓ Modificar configuración - Sí"));
        add(new Paragraph("✓ Leer todos los datos - Sí (acceso completo)"));
        add(new Paragraph("✓ Escribir en recursos críticos - Sí"));
        add(new Paragraph("✓ Acceso temporal - Sí"));
        add(new Paragraph("✓ Ver logs de auditoría - Sí"));
        
        add(new H2("ACCESO TOTAL: Puedes hacer todo en el sistema"));
    }
}
