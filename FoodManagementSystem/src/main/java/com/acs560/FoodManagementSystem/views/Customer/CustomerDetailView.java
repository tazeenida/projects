package com.acs560.FoodManagementSystem.views.Customer;

import com.acs560.FoodManagementSystem.models.Customer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

/**
 * The {@link CustomerDetailView} class represents a form to display and edit customer details.
 * It extends {@link FormLayout} and provides fields for the customer's rating.
 * The form is bound to a {@link Customer} object for data binding and validation.
 */
public class CustomerDetailView extends FormLayout {

    private static final long serialVersionUID = 476310807171214015L;

    private final TextField rating = new TextField("Rating");

    private final Binder<Customer> binder = new BeanValidationBinder<>(Customer.class);

    /**
     * Constructs a new instance of {@link CustomerDetailView}.
     * Initializes the form layout, binds fields to the {@link Customer} object,
     * and sets the width of the form.
     */
    public CustomerDetailView() {
        addClassName("customer-detail");

        binder.bindInstanceFields(this);

        add(rating);
        setWidth("25em");
    }

    /**
     * Updates the form with the specified customer's data for display purposes.
     * The customer object is bound to the form fields.
     *
     * @param customer the {@link Customer} object containing data to be displayed
     */
    public void setCustomer(Customer customer) {
        binder.readBean(customer);
    }

    /**
     * Clears the form fields when no customer is selected.
     * This method can be called to reset the form to its initial state.
     */
    public void clear() {
        binder.readBean(null);
    }
}
