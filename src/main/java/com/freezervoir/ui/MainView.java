package com.freezervoir.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("")
@PageTitle("Freeservoir")
public class MainView extends AppLayout {

    public MainView() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("Freeservoir ❄\uFE0F");
        addToNavbar(logo);
    }

}
