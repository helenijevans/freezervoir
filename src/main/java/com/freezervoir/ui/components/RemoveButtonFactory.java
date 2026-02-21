package com.freezervoir.ui.components;

import com.freezervoir.entity.LegacyFreezerItems;
import com.freezervoir.exception.ItemNotFoundException;
import com.freezervoir.service.LegacyFreezerItemsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.icon.VaadinIcon;

public class RemoveButtonFactory {

    public static Button create(
            LegacyFreezerItems item,
            LegacyFreezerItemsService service,
            Runnable afterRemove
    ) {
        Button remove = new Button("Remove Item", VaadinIcon.TRASH.create());

        remove.addThemeVariants(
                ButtonVariant.LUMO_ERROR,
                ButtonVariant.LUMO_PRIMARY
        );

        remove.addThemeVariants(
                ButtonVariant.LUMO_ERROR,
                ButtonVariant.LUMO_PRIMARY
        );

        remove.addClickListener(e -> {

            ConfirmDialog dialog = new ConfirmDialog();
            dialog.setHeader(String.format("Remove %s?", item.getItemId()));
            dialog.setText("This action cannot be undone.");
            dialog.setConfirmText("Remove");
            dialog.setConfirmButtonTheme("error primary");
            dialog.setCancelText("Cancel");
            dialog.setCancelable(true);
            dialog.setCloseOnEsc(true);

            dialog.addConfirmListener(ev -> {
                try {
                    service.deleteById(item.getItemId());
                } catch (ItemNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                afterRemove.run();
            });

            dialog.open();
        });

        return remove;
    }
}
