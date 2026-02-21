package com.freezervoir.ui;

import com.freezervoir.entity.LegacyFreezerItems;
import com.freezervoir.service.LegacyFreezerItemsService;
import com.freezervoir.ui.components.RemoveButtonFactory;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Route(value = "/legacy", layout = MainLayout.class)
@PageTitle("Freezervoir")
@RequiredArgsConstructor
public class LegacyMainView extends VerticalLayout {

    private final LegacyFreezerItemsService service;


    @PostConstruct
    private void init() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        add(createContent());
    }
    private Component createContent() {
        Grid<LegacyFreezerItems> grid = new Grid<>(LegacyFreezerItems.class, false);

        grid.addColumn(LegacyFreezerItems::getItemId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(LegacyFreezerItems::getDateAdded).setHeader("Date Added").setAutoWidth(true);
        grid.addColumn(LegacyFreezerItems::getNotes).setHeader("Notes").setAutoWidth(true);

        grid.addComponentColumn(item ->
                RemoveButtonFactory.create(
                        item,
                        service,
                        () -> grid.setItems(service.getAll())
                )
        ).setHeader("").setWidth("200px").setFlexGrow(0);

        grid.setItems(service.getAll());
        grid.setSizeFull();

        grid.addItemClickListener(event ->
                getUI().ifPresent(ui ->
                        ui.navigate("legacy/items/" + event.getItem().getItemId())
                )
        );

        Button addButton = new Button("Add Item", VaadinIcon.PLUS.create());
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Dialog formDialog = new Dialog();

        TextField itemIdField = new TextField("ID");
        TextField notesField = new TextField("Notes");
        DatePicker dateAddedField = new DatePicker("Date Added");

        Binder<LegacyFreezerItems> binder = new Binder<>(LegacyFreezerItems.class);

        binder.forField(itemIdField)
                .asRequired("ID required")
                .bind(LegacyFreezerItems::getItemId, LegacyFreezerItems::setItemId);

        binder.forField(notesField)
                .bind(LegacyFreezerItems::getNotes, LegacyFreezerItems::setNotes);

        binder.forField(dateAddedField)
                .asRequired("Date required")
                .bind(LegacyFreezerItems::getDateAdded, LegacyFreezerItems::setDateAdded);

        Button save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancel = new Button("Cancel");
        cancel.addClickListener(e -> formDialog.close());

        save.addClickListener(e -> {
            LegacyFreezerItems item = new LegacyFreezerItems();

            if (binder.writeBeanIfValid(item)) {
                service.saveItem(item);
                grid.setItems(service.getAll());
                formDialog.close();
            }
        });

        HorizontalLayout buttons = new HorizontalLayout(save, cancel);
        VerticalLayout formLayout = new VerticalLayout(
                itemIdField,
                notesField,
                dateAddedField,
                buttons
        );

        formDialog.add(formLayout);

        addButton.addClickListener(e -> {
            binder.readBean(null);
            formDialog.open();
        });


        VerticalLayout wrapper = new VerticalLayout(addButton, grid);
        wrapper.setPadding(true);
        wrapper.setSpacing(true);
        wrapper.setSizeFull();
        wrapper.expand(grid);

        return wrapper;
    }
}
