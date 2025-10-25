package com.example.application.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.security.Rol;
import com.example.application.security.ServicioAutenticacion;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.Scroller;ñ
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Layout
@AnonymousAllowed
public class DisposicionPrincipal extends AppLayout {

    private H1 tituloVista;
    private final ServicioAutenticacion servicioAutenticacion;

    @Autowired
    public DisposicionPrincipal(ServicioAutenticacion servicioAutenticacion) {
        this.servicioAutenticacion = servicioAutenticacion;
        setPrimarySection(Section.DRAWER);
        agregarContenidoCajon();
        agregarContenidoEncabezado();
    }

    private void agregarContenidoEncabezado() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Alternar menú");

        tituloVista = new H1();
        tituloVista.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, tituloVista);
    }

    private void agregarContenidoCajon() {
        Span nombreApp = new Span("Proyecto Integrador");
        nombreApp.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header encabezado = new Header(nombreApp);

        Scroller desplazador = new Scroller(crearNavegacion());

        addToDrawer(encabezado, desplazador, crearPiePagina());
    }

    private SideNav crearNavegacion() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Inicio", ""));

        Rol rol = Rol.USUARIO;
        try {
            rol = servicioAutenticacion.obtenerUsuarioActual().getRol();
        } catch (Exception e) {
        }

        if (rol == Rol.ADMIN) {
            nav.addItem(new SideNavItem("Admin", "admin"));
            nav.addItem(new SideNavItem("Empleado", "empleado"));
            nav.addItem(new SideNavItem("Usuario", "usuario"));
            nav.addItem(new SideNavItem("Invitado", "invitado"));
        } else if (rol == Rol.EMPLEADO) {
            nav.addItem(new SideNavItem("Empleado", "empleado"));
            nav.addItem(new SideNavItem("Usuario", "usuario"));
        } else if (rol == Rol.USUARIO) {
            nav.addItem(new SideNavItem("Usuario", "usuario"));
        } else if (rol == Rol.INVITADO) {
        }

        nav.addItem(new SideNavItem("Cambiar rol / Login", "login"));

        return nav;
    }

    private Footer crearPiePagina() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        tituloVista.setText(obtenerTituloPaginaActual());
    }

    private String obtenerTituloPaginaActual() {
        PageTitle titulo = getContent().getClass().getAnnotation(PageTitle.class);
        return titulo != null ? titulo.value() : "";
    }
}
