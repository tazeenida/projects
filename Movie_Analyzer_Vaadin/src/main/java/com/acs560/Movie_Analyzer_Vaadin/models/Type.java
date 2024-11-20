package com.acs560.Movie_Analyzer_Vaadin.models;

import com.acs560.Movie_Analyzer_Vaadin.entities.TypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the Type model in the application.
 * <p>
 * This class is used to transfer type (genre or category) data related to movies
 * between layers of the application, such as between the controller and service layers.
 * It contains properties that describe a type, including its unique identifier and name.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Type {
    
    /**
     * The unique identifier for the type.
     */
    private int typeId;

    /**
     * The name of the type (genre or category).
     */
    private String type;

    /**
     * Constructs a new Type model from a TypeEntity.
     *
     * @param typeEntity The TypeEntity to convert into a Type model.
     */
    public Type(TypeEntity typeEntity) {
        this.typeId = typeEntity.getTypeId();
        this.type = typeEntity.getType();
    }
}
