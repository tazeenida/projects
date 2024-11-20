package com.acs560.Movie_Analyzer_Vaadin.views.types;

import com.acs560.Movie_Analyzer_Vaadin.models.Type;
import com.acs560.Movie_Analyzer_Vaadin.entities.TypeEntity;
import com.acs560.Movie_Analyzer_Vaadin.services.TypeService;
import com.acs560.Movie_Analyzer_Vaadin.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;

import jakarta.annotation.security.PermitAll;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;

/**
 * The view for managing types in the Movie Analyzer application.
 * This view allows users to create, update, and delete movie types.
 */
@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "types", layout = MainLayout.class)
@PageTitle("Movies | Movies Analyzer")
public class TypeView extends VerticalLayout {

    private final TypeService typeService;  
    private final Grid<Type> grid = new Grid<>(Type.class);
    private final TextField typeNameField = new TextField("Type Name");
    private final TextField filterField = new TextField("Filter by Type Name");
    
    private Type currentType;

    /**
     * Constructor for TypeView.
     * Initializes the view with the provided TypeService and configures the grid.
     *
     * @param typeService The service used to manage types.
     */
    public TypeView(TypeService typeService) {
        this.typeService = typeService;
        configureGrid();
        add(createFilterLayout(), typeNameField, createButtons(), grid);
        updateList();
    }

    /**
     * Creates a horizontal layout containing the filter field.
     *
     * @return A HorizontalLayout containing the filter text field.
     */
    private HorizontalLayout createFilterLayout() {
        filterField.setPlaceholder("Filter by Type Name...");
        filterField.addValueChangeListener(event -> filterTypes());
        return new HorizontalLayout(filterField);
    }

    /**
     * Filters the types displayed in the grid based on the input in the filter field.
     */
    private void filterTypes() {
        String filterText = filterField.getValue().toLowerCase();
        List<TypeEntity> types = typeService.getAllTypes();
        
        List<TypeEntity> filteredTypes = types.stream()
            .filter(typeEntity -> typeEntity.getType() != null && 
                typeEntity.getType().toLowerCase().contains(filterText))
            .collect(Collectors.toList());

        grid.setItems(filteredTypes.stream().map(Type::new).toList());
    }

    /**
     * Configures the grid for displaying types.
     * Sets columns and adds a listener for selection changes.
     */
    private void configureGrid() {
        grid.setColumns("typeId", "type");
        grid.asSingleSelect().addValueChangeListener(event -> {
            currentType = event.getValue();
            if (currentType != null) {
                typeNameField.setValue(currentType.getType());
            } else {
                clearForm(); 
            }
        });
    }

    /**
     * Creates a horizontal layout containing action buttons for the form.
     *
     * @return A HorizontalLayout containing the save and delete buttons.
     */
    private HorizontalLayout createButtons() {
        Button saveButton = new Button("Save", event -> saveType());
        Button deleteButton = new Button("Delete", event -> deleteType());
        return new HorizontalLayout(saveButton, deleteButton);
    }

    /**
     * Saves the current type or creates a new type based on the input.
     * Displays a notification indicating the action performed.
     */
    private void saveType() {
        if (currentType == null) {
            Type newType = new Type();
            newType.setType(typeNameField.getValue());
            typeService.addType(new TypeEntity(newType)); 
            Notification.show("Type created!");
        } else {
            currentType.setType(typeNameField.getValue());
            typeService.updateType(currentType.getTypeId(), new TypeEntity(currentType));
            Notification.show("Type updated!");
        }
        clearForm();
        updateList();
    }

    /**
     * Deletes the currently selected type, if any.
     * Displays a notification indicating the deletion.
     */
    private void deleteType() {
        if (currentType != null) {
            typeService.deleteType(currentType.getTypeId());
            Notification.show("Type deleted!");
            clearForm();
            updateList();
        }
    }

    /**
     * Clears the input fields and resets the selected type.
     */
    private void clearForm() {
        typeNameField.clear();
        currentType = null;
        grid.deselectAll();
    }

    /**
     * Updates the list of types displayed in the grid.
     * Fetches all types from the service and sets them in the grid.
     */
    private void updateList() {
        List<TypeEntity> types = typeService.getAllTypes(); 
        grid.setItems(types.stream().map(Type::new).toList());
    }
}
