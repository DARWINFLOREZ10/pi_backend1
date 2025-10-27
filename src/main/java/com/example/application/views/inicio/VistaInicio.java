package com.example.application.views.inicio;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.example.application.views.DisposicionPrincipal;

@PageTitle("Inicio")
@Route(value = "", layout = DisposicionPrincipal.class)
@Menu(order = 0, icon = LineAwesomeIconUrl.FILE)
public class VistaInicio extends VerticalLayout {

    public VistaInicio() {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("Bienvenido al Sistema de Control de Acceso");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("Este es un sistema de control de acceso basado en roles 🔐"));
        add(new Paragraph("Ve a 'Cambiar rol / Login' para seleccionar tu rol y explorar las diferentes secciones."));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }
}
