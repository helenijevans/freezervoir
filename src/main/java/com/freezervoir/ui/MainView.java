package com.freezervoir.ui;

import com.freezervoir.entity.FreezerItems;
import com.freezervoir.repository.FreezerItemsRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Route("")
@PageTitle("Freezervoir")
@RequiredArgsConstructor
public class MainView extends AppLayout {

    private final FreezerItemsRepository repository;

    @PostConstruct
    private void init() {
        createHeader();
        setContent(createContent());
    }

    private void createHeader() {
        H1 logo = new H1("Freezervoir ❄️");
        addToNavbar(logo);
    }

    private Component createContent() {
        Grid<FreezerItems> grid = new Grid<>(FreezerItems.class, false);

        grid.addColumn(FreezerItems::getItemId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(FreezerItems::getDateAdded).setHeader("Date Added").setAutoWidth(true);
        grid.addColumn(FreezerItems::getNotes).setHeader("Notes").setAutoWidth(true);

        grid.addComponentColumn(item -> {
            Button delete = new Button(VaadinIcon.TRASH.create());
            delete.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY);

            delete.addClickListener(e -> {
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setHeader("Delete item?");
                dialog.setText("This action cannot be undone.");
                dialog.setConfirmText("Delete");
                dialog.setConfirmButtonTheme("error primary");
                dialog.setCancelText("Cancel");

                dialog.addConfirmListener(ev -> {
                    repository.delete(item);
                    grid.setItems(repository.findAll());
                });
                dialog.setCancelable(true);
                dialog.setCloseOnEsc(true);

                dialog.open();
            });


            return delete;
        }).setHeader("").setAutoWidth(true);

        grid.setItems(repository.findAll());
        grid.setSizeFull();

        VerticalLayout wrapper = new VerticalLayout(grid);
        wrapper.setPadding(true);
        wrapper.setSpacing(true);
        wrapper.setSizeFull();
        wrapper.expand(grid);

        return wrapper;
    }
}
