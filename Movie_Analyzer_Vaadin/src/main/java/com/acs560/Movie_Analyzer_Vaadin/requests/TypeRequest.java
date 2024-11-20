package com.acs560.Movie_Analyzer_Vaadin.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents a request to create or update a movie type (genre or category).
 * <p>
 * This class is used to encapsulate the data sent by the client to the server
 * when a request to create or update a type is made.
 * </p>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TypeRequest {

    /**
     * The name of the type (genre or category).
     */
    private String name;
}
