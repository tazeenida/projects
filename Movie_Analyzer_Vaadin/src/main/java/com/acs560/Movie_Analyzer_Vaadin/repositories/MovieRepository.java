package com.acs560.Movie_Analyzer_Vaadin.repositories;

import java.util.List;

import com.acs560.Movie_Analyzer_Vaadin.entities.MovieEntity;
import com.acs560.Movie_Analyzer_Vaadin.entities.TypeEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing movie data.
 * <p>
 * This interface provides methods for CRUD operations on MovieEntity objects,
 * leveraging Spring Data JPA's CrudRepository.
 * </p>
 */
@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Integer> {

    /**
     * Retrieves all movies from the repository.
     *
     * @return List of all MovieEntity objects stored in the repository.
     */
    List<MovieEntity> findAll();

    /**
     * Retrieves movies by ID.
     *
     * @param id The ID of the movie to retrieve.
     * @return List of MovieEntity objects matching the specified ID.
     *         If no movie is found, an empty list is returned.
     */
    List<MovieEntity> findById(int id);

    /**
     * Retrieves movies by title.
     *
     * @param title The title of the movies to search for.
     * @return List of MovieEntity objects that match the specified title.
     */
    List<MovieEntity> findByTitle(String title);

    /**
     * Retrieves movies by release year.
     *
     * @param releaseYear The release year of the movies to search for.
     * @return List of MovieEntity objects released in the specified year.
     */
    List<MovieEntity> findByReleaseYear(int releaseYear);

    /**
     * Retrieves movies by director.
     *
     * @param director The director of the movies to search for.
     * @return List of MovieEntity objects directed by the specified director.
     */
    List<MovieEntity> findByDirector(String director);

    /**
     * Retrieves movies by type (e.g., Action, Comedy).
     *
     * @param type The type of the movies to search for.
     * @return List of MovieEntity objects that match the specified type.
     */
    List<MovieEntity> findByType(TypeEntity type);

    
    /**
     * Finds movies by title, director, and release year.
     * <p>
     * This method is primarily used to check for duplicate entries in the repository.
     * </p>
     *
     * @param title        The title of the movie.
     * @param director     The director of the movie.
     * @param releaseYear  The release year of the movie.
     * @return List of MovieEntity objects matching the specified criteria.
     */
    List<MovieEntity> findByTitleAndDirectorAndReleaseYear(String title, String director, int releaseYear);
}
