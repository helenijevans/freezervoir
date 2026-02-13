package com.freezervoir.ui;

import com.freezervoir.entity.FreezerItems;
import com.freezervoir.repository.FreezerItemsRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
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
        HorizontalLayout header = new HorizontalLayout(logo);
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER
        );
        header.setPadding(true);
        header.setSpacing(false);

        addToNavbar(header);
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
        }).setHeader("").setWidth("100px").setFlexGrow(0);

        grid.setItems(repository.findAll());
        grid.setSizeFull();

        Button addButton = new Button("Add Item", VaadinIcon.PLUS.create());
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Dialog formDialog = new Dialog();

        TextField itemIdField = new TextField("ID");
        TextField notesField = new TextField("Notes");
        DatePicker dateAddedField = new DatePicker("Date Added");

        Binder<FreezerItems> binder = new Binder<>(FreezerItems.class);

        binder.forField(itemIdField)
                .asRequired("ID required")
                .bind(FreezerItems::getItemId, FreezerItems::setItemId);

        binder.forField(notesField)
                .bind(FreezerItems::getNotes, FreezerItems::setNotes);

        binder.forField(dateAddedField)
                .asRequired("Date required")
                .bind(FreezerItems::getDateAdded, FreezerItems::setDateAdded);

        Button save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancel = new Button("Cancel");
        cancel.addClickListener(e -> formDialog.close());

        save.addClickListener(e -> {
            FreezerItems item = new FreezerItems();

            if (binder.writeBeanIfValid(item)) {
                repository.save(item);
                grid.setItems(repository.findAll());
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
