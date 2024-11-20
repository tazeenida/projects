package com.acs560.Movie_Analyzer_Vaadin.views.types;

import com.acs560.Movie_Analyzer_Vaadin.models.Type;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import lombok.Getter;

/**
 * The form to manage a type in the Movie Analyzer application.
 * This form allows users to add, update, and delete types.
 */
public class TypeForm extends FormLayout {

    private static final long serialVersionUID = 476310807171214015L;

    private final TextField name = new TextField("Type");

    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");
    private final Button cancel = new Button("Cancel");

    private final Binder<Type> binder = new BeanValidationBinder<>(Type.class);
    private Type type;
    private boolean isAdd;

    /**
     * Constructor to initialize the TypeForm.
     * Sets up the form layout and binds fields to the Type model.
     */
    public TypeForm() {
        addClassName("type-form");

        binder.bindInstanceFields(this);

        add(name, createButtonsLayout());
        setWidth("25em");
    }

    /**
     * Creates the buttons layout for the form.
     * 
     * @return A HorizontalLayout containing the action buttons.
     */
    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> handleSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        cancel.addClickListener(event -> fireEvent(new CancelEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, cancel);
    }

    /**
     * Handles the save action.
     * Writes the data from the form to the Type model and fires the appropriate event.
     */
    private void handleSave() {
        try {
            binder.writeBean(type);

            if (isAdd) {
                fireEvent(new AddEvent(this, type));
            } else {
                fireEvent(new UpdateEvent(this, type));
            }

        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the form with the specified type and whether it is for adding or updating a type.
     *
     * @param type  The type to be managed by the form.
     * @param isAdd True if this form is for adding a new type, false if it is for updating an existing type.
     */
    public void update(Type type, boolean isAdd) {
        this.isAdd = isAdd;

        delete.setVisible(!isAdd);

        if (type != null) {
            this.type = type;
        } else {
            name.setValue("");
            this.type = new Type();
        }

        binder.setBean(this.type);
    }

    /**
     * Abstract class representing an event from the TypeForm.
     */
    public static abstract class TypeFormEvent extends ComponentEvent<TypeForm> {

        private static final long serialVersionUID = 8892029064323709532L;

        @Getter
        private final Type type;

        /**
         * Constructor for TypeFormEvent.
         *
         * @param source The TypeForm that generated the event.
         * @param type   The type associated with the event.
         */
        protected TypeFormEvent(TypeForm source, Type type) {
            super(source, false);
            this.type = type;
        }
    }

    /**
     * Event for adding a type.
     */
    public static class AddEvent extends TypeFormEvent {

        private static final long serialVersionUID = -8168200990060394704L;

        /**
         * Constructor for AddEvent.
         *
         * @param source The TypeForm that generated the event.
         * @param type   The type being added.
         */
        protected AddEvent(TypeForm source, Type type) {
            super(source, type);
        }
    }

    /**
     * Event for canceling an action in the form.
     */
    public static class CancelEvent extends TypeFormEvent {

        private static final long serialVersionUID = -6473184605060760145L;

        /**
         * Constructor for CancelEvent.
         *
         * @param source The TypeForm that generated the event.
         */
        protected CancelEvent(TypeForm source) {
            super(source, null);
        }
    }

    /**
     * Event for deleting a type.
     */
    public static class DeleteEvent extends TypeFormEvent {

        private static final long serialVersionUID = -5085028297106734234L;

        /**
         * Constructor for DeleteEvent.
         *
         * @param source The TypeForm that generated the event.
         * @param type   The type being deleted.
         */
        protected DeleteEvent(TypeForm source, Type type) {
            super(source, type);
        }
    }

    /**
     * Event for updating a type.
     */
    public static class UpdateEvent extends TypeFormEvent {

        private static final long serialVersionUID = -5085028297106734234L;

        /**
         * Constructor for UpdateEvent.
         *
         * @param source The TypeForm that generated the event.
         * @param type   The type being updated.
         */
        protected UpdateEvent(TypeForm source, Type type) {
            super(source, type);
        }
    }

    /**
     * Adds a listener for a specific event type.
     *
     * @param eventType The event type for which to call the listener.
     * @param listener  The listener to be added.
     * @return An object that can be used to remove the listener.
     */
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
            ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
