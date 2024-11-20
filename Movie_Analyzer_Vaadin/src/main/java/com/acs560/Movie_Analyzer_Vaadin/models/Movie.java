package com.acs560.Movie_Analyzer_Vaadin.models;

import com.acs560.Movie_Analyzer_Vaadin.entities.TypeEntity;
import lombok.*;

/**
 * Represents the Movie model in the application.
 * <p>
 * This class is used to transfer movie data between layers of the application,
 * such as between the controller and service layers. It contains properties
 * that describe a movie, including its title, director, release year, countries,
 * and type information.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {
    
    /**
     * The unique identifier for the movie.
     */
    private int id;

    /**
     * The title of the movie.
     */
    private String title;

    /**
     * The director of the movie.
     */
    private String director;

    /**
     * The year the movie was released.
     */
    private int releaseYear;

    /**
     * The countries where the movie was produced.
     */
    private String countries;

    /**
     * The type ID associated with the movie, representing its genre or category.
     */
    private int typeId;

    /**
     * Retrieves the type ID of the movie.
     *
     * @return The type ID.
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * Sets the type ID for the movie.
     *
     * @param typeId The unique identifier for the type associated with the movie.
     */
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
