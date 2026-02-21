package com.freezervoir.ui;

import com.freezervoir.entity.LegacyFreezerItems;
import com.freezervoir.exception.ItemNotFoundException;
import com.freezervoir.service.LegacyFreezerItemsService;
import com.freezervoir.ui.components.RemoveButtonFactory;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.*;
import lombok.RequiredArgsConstructor;

@Route(value = "legacy/items/:id", layout = MainLayout.class)
@PageTitle("Item Details")
@RequiredArgsConstructor
public class LegacyItemView extends VerticalLayout implements BeforeEnterObserver {

    private final LegacyFreezerItemsService service;

    private LegacyFreezerItems item;

    private final H1 title = new H1();
    private final Span dateAdded = new Span();
    private final TextArea notes = new TextArea();
    private final Span saveFeedback = new Span();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        String id = event.getRouteParameters().get("id").orElse(null);

        if (id == null) {
            event.forwardTo(LegacyMainView.class);
            return;
        }


        try {
            item = service.getById(id);
        } catch (ItemNotFoundException e) {
            event.forwardTo(MissingItemView.class);
            return;
        }

        buildLayout();

    }

    private void buildLayout() {

        removeAll();

        setPadding(true);
        setSpacing(true);
        setMaxWidth("600px");

        title.setText(item.getItemId());
        title.getStyle().set("margin-bottom", "0");

        dateAdded.setText("Stored since: " + item.getDateAdded());
        dateAdded.getStyle()
                .set("color", "var(--lumo-secondary-text-color)");

        notes.setWidthFull();
        notes.setMinHeight("100px");
        notes.setPlaceholder("Add notes...");
        notes.setValue(item.getNotes() != null ? item.getNotes() : "");

        notes.getStyle()
                .set("--vaadin-input-field-background", "#fbffbc") // inner area
                .set("background-color", "white");                 // outer container


        notes.getElement().addEventListener("dblclick", e -> {
            notes.focus();
        });

        saveFeedback.getStyle()
                .set("color", "var(--lumo-success-color)")
                .set("font-size", "0.9rem");

        notes.addBlurListener(e -> {

            String updated = notes.getValue().trim();
            String oldValue = item.getNotes() == null ? "" : item.getNotes();
            if (!updated.equals(oldValue)) {
                item.setNotes(updated.isEmpty() ? null : updated);

                service.saveItem(item);

                saveFeedback.setText("Saved ✓");

                getUI().ifPresent(ui ->
                        ui.getPage().executeJs(
                                "setTimeout(() => $0.textContent = '', 2000);",
                                saveFeedback.getElement()
                        )
                );
            }

        });

        Button deleteButton = RemoveButtonFactory.create(
                item,
                service,
                () -> getUI().ifPresent(ui -> ui.navigate(""))
        );

        add(title, dateAdded, notes, saveFeedback, deleteButton);
    }
}
