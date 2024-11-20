package com.acs560.Movie_Analyzer_Vaadin.services;

import com.acs560.Movie_Analyzer_Vaadin.entities.TypeEntity;
import com.acs560.Movie_Analyzer_Vaadin.requests.TypeRequest;

import java.util.List;

/**
 * Service interface for managing movie types.
 * <p>
 * This interface defines the methods for performing CRUD operations
 * on movie types, including retrieving, adding, updating, and deleting
 * types in the persistence layer.
 * </p>
 */
public interface TypeService {

    /**
     * Retrieves all types from the repository.
     *
     * @return A list of all {@link TypeEntity} objects stored in the repository.
     */
    List<TypeEntity> getAllTypes();

    
    /**
     * Retrieves a list of types that match the specified type name.
     *
     * @param type The name of the type to search for.
     * @return A list of {@link TypeEntity} that match the given type.
     */
    List<TypeEntity> getByType(String type);
    
    /**
     * Retrieves a type by its ID.
     *
     * @param id The ID of the type to retrieve.
     * @return The {@link TypeEntity} associated with the provided ID.
     * @throws IllegalArgumentException if no type with the specified ID exists.
     */
    TypeEntity getTypeById(int id);

    /**
     * Adds a new type to the repository.
     *
     * @param tr The {@link TypeEntity} to add.
     * @throws IllegalArgumentException if a type with the same name already exists.
     */
    void addType(TypeRequest tr);

    /**
     * Updates an existing type in the repository.
     *
     * @param id The ID of the type to update.
     * @param typeEntity The updated {@link TypeEntity}.
     * @throws IllegalArgumentException if no type with the specified ID exists.
     */
    void updateType(int id,TypeRequest tr);

    /**
     * Deletes a type from the repository by its ID.
     *
     * @param id The ID of the type to delete.
     * @throws IllegalArgumentException if no type with the specified ID exists.
     */
    void deleteType(int id);


    void addType(TypeEntity type);


	void updateType(int id, TypeEntity typeEntity);

}
