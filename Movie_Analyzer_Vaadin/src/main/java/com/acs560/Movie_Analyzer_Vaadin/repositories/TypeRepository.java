package com.acs560.Movie_Analyzer_Vaadin.repositories;

import java.util.Optional;
import com.acs560.Movie_Analyzer_Vaadin.entities.TypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing type data.
 * <p>
 * This interface provides methods for CRUD operations on TypeEntity objects,
 * leveraging Spring Data JPA's CrudRepository.
 * </p>
 */
@Repository
public interface TypeRepository extends CrudRepository<TypeEntity, Integer> {

    /**
     * Retrieves a type by ID.
     *
     * @param id The ID of the type to retrieve.
     * @return Optional<TypeEntity> containing the TypeEntity associated with the provided ID.
     */
    Optional<TypeEntity> findById(int id);

    /**
     * Retrieves a type by name.
     *
     * @param typeName The name of the type to retrieve.
     * @return Optional<TypeEntity> containing the TypeEntity associated with the provided type name.
     */
    Optional<TypeEntity> findByType(String type);
}
