package com.acs560.Movie_Analyzer_Vaadin.entities;

import com.acs560.Movie_Analyzer_Vaadin.models.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents the Type entity in the persistence layer.
 * <p>
 * This class is mapped to the "type" table and contains information about the type of a movie.
 * The type can represent various categories such as Action, Comedy, Drama, etc.
 * It uses Lombok annotations to reduce boilerplate code for getters, setters, constructors,
 * and methods for equality checks and string representation.
 * </p>
 */
@Entity
@Table(name = "type")
@Getter
@Setter 
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TypeEntity {

    /**
     * The unique identifier for the type.
     */
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeId;

    /**
     * The name of the type. This field must be unique in the database.
     */
    @Column(name = "type", unique = true, nullable = false)
    private String type;

    /**
     * Constructor that creates a TypeEntity from a Type model.
     *
     * @param c The Type model containing the type details.
     */
    public TypeEntity(Type c) {
        this(c.getTypeId(), c.getType());
    }

    /**
     * Constructor that allows setting the type ID.
     *
     * @param typeId The unique identifier for the type.
     */
    public TypeEntity(int typeId) {
        this.typeId = typeId;
    }
}
