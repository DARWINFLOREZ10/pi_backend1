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

@PageTitle("Empleado")
@Route(value = "empleado", layout = DisposicionPrincipal.class)
public class VistaEmpleado extends VerticalLayout {

    @Autowired
    public VistaEmpleado(ServicioAutenticacion servicioAutenticacion) {
        Rol rol = servicioAutenticacion.obtenerUsuarioActual().getRol();
        if (rol != Rol.ADMIN && rol != Rol.EMPLEADO) {
            UI.getCurrent().navigate("");
            add(new H2("Acceso denegado. Redirigiendo..."));
            return;
        }

        add(new H2("Panel de Empleado"));
        add(new H2("Usuario: " + servicioAutenticacion.obtenerUsuarioActual().getNombreUsuario()));
        add(new H2("Rol: EMPLEADO"));
        
        add(new H2("Tus permisos en el sistema:"));
        add(new Paragraph("✓ Leer datos - Parcial (solo algunos datos)"));
        add(new Paragraph("✓ Escribir en recursos críticos - Según tu puesto"));
        add(new Paragraph("✓ Acceso temporal - Sí (si aplica a tu rol)"));
        add(new Paragraph("✓ Ver logs de auditoría - Limitado"));
        
        add(new H2("Restricciones:"));
        add(new Paragraph("✗ Crear usuarios - No permitido"));
        add(new Paragraph("✗ Modificar configuración - No permitido"));
    }
}
