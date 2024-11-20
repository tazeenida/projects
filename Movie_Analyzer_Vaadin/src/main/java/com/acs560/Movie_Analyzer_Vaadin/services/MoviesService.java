package com.acs560.Movie_Analyzer_Vaadin.services;

import org.springframework.stereotype.Service;

import com.acs560.Movie_Analyzer_Vaadin.entities.MovieEntity;
import com.acs560.Movie_Analyzer_Vaadin.models.Movie;
import com.acs560.Movie_Analyzer_Vaadin.repositories.MovieRepository;

import java.util.List;

/**
 * Service class for handling business logic related to movies.
 * <p>
 * This class provides methods to access and manipulate movie data using the {@link MovieRepository}.
 * It offers various methods to retrieve movies based on different criteria and to manage movie records.
 * </p>
 */
@Service
public interface MoviesService {
    
    /**
     * Retrieves movies from the repository by ID.
     *
     * @param id The ID of the movie to retrieve.
     * @return List of MovieEntity objects matching the specified ID.
     *         If no movie is found, an empty list is returned.
     */
    public List<MovieEntity> getMoviesById(int id);
    
    /**
     * Retrieves all movies from the repository.
     *
     * @return List of all MovieEntity objects stored in the repository.
     */
    public List<MovieEntity> getMovies();
    
    /**
     * Retrieves movies by title.
     *
     * @param title The title of the movies to search for.
     * @return List of MovieEntity objects that match the specified title.
     */
    public List<MovieEntity> getMoviesByTitle(String title);
       
    /**
     * Retrieves movies by director.
     *
     * @param director The director of the movies to search for.
     * @return List of MovieEntity objects directed by the specified director.
     */
    public List<MovieEntity> getMoviesByDirector(String director);

    /**
     * Retrieves movies by type (e.g., Action, Comedy).
     *
     * @param type The type of the movies to search for.
     * @return List of MovieEntity objects that match the specified type.
     */
    public List<MovieEntity> getMoviesByType(String type); 

    /**
     * Retrieves movies by release year.
     *
     * @param releaseYear The release year of the movies to search for.
     * @return List of MovieEntity objects released in the specified year.
     */
    public List<MovieEntity> getMoviesByReleaseYear(int releaseYear);

    /**
     * Adds a new movie to the repository.
     * 
     * This method accepts a Movie object and adds it to the repository.
     * The movie should include all required fields necessary for its storage.
     * 
     * @param movie The Movie object to be added. This object should
     *              contain the movie's details including title, director, type,
     *              countries, and release year.
     * @throws IllegalArgumentException if there is an error adding the movie
     *                                             (e.g., if the movie already exists).
     */
    void add(Movie movie);

    /**
     * Updates an existing movie.
     *
     * @param id The ID of the movie to update.
     * @param movie The Movie model with updated details.
     * @throws IllegalArgumentException if the movie with the specified ID does not exist.
     */
    void update(int id, Movie movie);

    /**
     * Deletes a movie from the repository.
     * 
     * This method removes the corresponding movie record from the repository
     * based on the specified ID.
     * 
     * @param id The ID of the movie to be deleted. The movie should exist in
     *           the repository to be successfully deleted.
     * @throws IllegalArgumentException if the movie with the specified ID does not exist.
     */
    void delete(int id);
}
