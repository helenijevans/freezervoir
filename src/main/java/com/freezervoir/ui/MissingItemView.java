package com.freezervoir.ui;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "/404", layout = MainLayout.class)
@PageTitle("Item Not Found")
public class MissingItemView extends VerticalLayout {

    public MissingItemView() {

        Image image = new Image("images/ItemNotFound.png", "Item not found");

        image.setMaxWidth("100%");
        image.setHeight("auto");

        setSizeFull();
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        add(image);
    }
}