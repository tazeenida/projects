package com.acs560.Movie_Analyzer_Vaadin.views.movies;

import com.acs560.Movie_Analyzer_Vaadin.entities.MovieEntity;
import com.acs560.Movie_Analyzer_Vaadin.models.Movie;
import com.acs560.Movie_Analyzer_Vaadin.services.MoviesService;
import com.acs560.Movie_Analyzer_Vaadin.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.annotation.SpringComponent;

import jakarta.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The view for managing movies in the Movies Analyzer application.
 */
@AnonymousAllowed
@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "", layout = MainLayout.class)
@PageTitle("Movies | Movies Analyzer")
public class MovieView extends VerticalLayout {

    private final MoviesService moviesService;
    private final Grid<MovieEntity> grid = new Grid<>(MovieEntity.class);
    private final TextField titleField = new TextField("Title");
    private final TextField directorField = new TextField("Director");
    private final TextField releaseYearField = new TextField("Release Year");
    private final TextField typeIdField = new TextField("Type Id");
    private final TextField countriesField = new TextField("Countries");

    private final TextField filterTitleField = new TextField();
    private final TextField filterDirectorField = new TextField();
    private final TextField filterYearField = new TextField();
    private final TextField filterTypeIdField = new TextField();
    private final TextField filterCountriesField = new TextField();

    /**
     * Constructs a new {@code MovieView} instance.
     * 
     * @param moviesService the service used to manage movies
     */
    @Autowired
    public MovieView(MoviesService moviesService) {
        this.moviesService = moviesService;
        configureGrid();
        
        HorizontalLayout mainLayout = new HorizontalLayout(createFilterPanel(), createContentLayout());
        mainLayout.setSizeFull();
        
        add(mainLayout);
        refreshGrid();
    }

    /**
     * Configures the grid by setting columns and listeners.
     */
    private void configureGrid() {
        grid.setColumns("id", "title", "director", "releaseYear", "type.typeId", "countries");
        grid.asSingleSelect().addValueChangeListener(event -> {
            MovieEntity selectedMovie = event.getValue();
            if (selectedMovie != null) {
                populateForm(selectedMovie);
            } else {
                clearForm();
            }
        });
    }

    /**
     * Creates a layout for filter fields and buttons.
     * 
     * @return the filtering panel
     */
    private Component createFilterPanel() {
        filterTitleField.setPlaceholder("Filter by Title...");
        filterDirectorField.setPlaceholder("Filter by Director...");
        filterYearField.setPlaceholder("Filter by Year...");
        filterTypeIdField.setPlaceholder("Filter by Type ID...");
        filterCountriesField.setPlaceholder("Filter by Countries...");

        Button filterButton = new Button("Filter", event -> filterMovies());
        
        VerticalLayout filterLayout = new VerticalLayout(
            filterTitleField,
            filterDirectorField,
            filterYearField,
            filterTypeIdField,
            filterCountriesField,
            filterButton
        );
        filterLayout.setSpacing(true);
        filterLayout.setWidth("300px");
        return filterLayout;
    }

    /**
     * Creates a layout for the main content area (form and grid).
     * 
     * @return the content layout
     */
    private Component createContentLayout() {
        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.add(createFormLayout(), createButtonLayout(), grid);
        contentLayout.setSizeFull();
        return contentLayout;
    }

    /**
     * Filters the movies grid based on the user inputs in filter fields, allowing partial matches.
     */
    private void filterMovies() {
        List<MovieEntity> filteredMovies = moviesService.getMovies().stream()
            .filter(movie -> {
                boolean matchesTitle = filterTitleField.isEmpty() || movie.getTitle().toLowerCase().contains(filterTitleField.getValue().toLowerCase());
                boolean matchesDirector = filterDirectorField.isEmpty() || movie.getDirector().toLowerCase().contains(filterDirectorField.getValue().toLowerCase());
                boolean matchesYear = filterYearField.isEmpty() || String.valueOf(movie.getReleaseYear()).contains(filterYearField.getValue());
                boolean matchesTypeId = filterTypeIdField.isEmpty() || String.valueOf(movie.getType().getTypeId()).contains(filterTypeIdField.getValue());
                boolean matchesCountries = filterCountriesField.isEmpty() || 
                    (movie.getCountries() != null && movie.getCountries().toLowerCase().contains(filterCountriesField.getValue().toLowerCase().trim()));

                return matchesTitle && matchesDirector && matchesYear && matchesTypeId && matchesCountries;
            })
            .collect(Collectors.toList());

        grid.setItems(filteredMovies);
    }


    /**
     * Creates the layout for form fields.
     */
    private Component createFormLayout() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setSpacing(true);
        
        titleField.setPlaceholder("Enter movie title");
        directorField.setPlaceholder("Enter director's name");
        releaseYearField.setPlaceholder("Enter release year");
        typeIdField.setPlaceholder("Enter Type ID");
        countriesField.setPlaceholder("Enter countries");

        formLayout.add(titleField, directorField, releaseYearField, typeIdField, countriesField);
        return formLayout;
    }

    /**
     * Creates a button layout with Add, Update, and Delete buttons.
     */
    private Component createButtonLayout() {
        Button addButton = new Button("Add Movie");
        Button updateButton = new Button("Update Movie");
        Button deleteButton = new Button("Delete Movie");
        Button clearButton = new Button("Clear Form");

        addButton.addClickListener(e -> addMovie());
        updateButton.addClickListener(e -> updateMovie());
        deleteButton.addClickListener(e -> deleteMovie());
        clearButton.addClickListener(e -> clearForm());

        HorizontalLayout buttonLayout = new HorizontalLayout(addButton, updateButton, deleteButton, clearButton);
        buttonLayout.setSpacing(true);
        return buttonLayout;
    }

    /**
     * Adds a new movie based on the entered details in the form fields.
     */
    private void addMovie() {
        try {
            Movie movie = new Movie();
            movie.setTitle(titleField.getValue());
            movie.setDirector(directorField.getValue());
            movie.setReleaseYear(Integer.parseInt(releaseYearField.getValue()));
            movie.setTypeId(Integer.parseInt(typeIdField.getValue()));
            movie.setCountries(countriesField.getValue());

            moviesService.add(movie);
            Notification.show("Movie added successfully!");
            refreshGrid();
            clearForm();
        } catch (Exception e) {
            Notification.show("Failed to add movie: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
        }
    }

    /**
     * Updates the selected movie based on the entered details.
     */
    private void updateMovie() {
        MovieEntity selectedMovie = grid.asSingleSelect().getValue();
        if (selectedMovie == null) {
            Notification.show("Please select a movie to update.");
            return;
        }

        try {
            Movie movie = new Movie();
            movie.setId(selectedMovie.getId());
            movie.setTitle(titleField.getValue());
            movie.setDirector(directorField.getValue());
            movie.setReleaseYear(Integer.parseInt(releaseYearField.getValue()));
            movie.setTypeId(Integer.parseInt(typeIdField.getValue()));
            movie.setCountries(countriesField.getValue());

            moviesService.update(selectedMovie.getId(), movie);
            Notification.show("Movie updated successfully!");
            refreshGrid();
            clearForm();
        } catch (Exception e) {
            Notification.show("Failed to update movie: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
        }
    }

    /**
     * Deletes the selected movie from the grid.
     */
    private void deleteMovie() {
        MovieEntity selectedMovie = grid.asSingleSelect().getValue();
        if (selectedMovie == null) {
            Notification.show("Please select a movie to delete.");
            return;
        }

        try {
            moviesService.delete(selectedMovie.getId());
            Notification.show("Movie deleted successfully!");
            refreshGrid();
            clearForm();
        } catch (Exception e) {
            Notification.show("Failed to delete movie: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
        }
    }

    /**
     * Refreshes the movie grid with the latest data.
     */
    private void refreshGrid() {
        grid.setItems(moviesService.getMovies());
    }

    /**
     * Populates the form with data from the selected movie.
     */
    private void populateForm(MovieEntity movie) {
        titleField.setValue(movie.getTitle());
        directorField.setValue(movie.getDirector());
        releaseYearField.setValue(String.valueOf(movie.getReleaseYear()));
        typeIdField.setValue(String.valueOf(movie.getType().getTypeId()));
        countriesField.setValue(movie.getCountries());
    }

    /**
     * Clears the form fields.
     */
    private void clearForm() {
        titleField.clear();
        directorField.clear();
        releaseYearField.clear();
        typeIdField.clear();
        countriesField.clear();
        grid.asSingleSelect().clear();
    }
}
