package com.acs560.Movie_Analyzer_Vaadin.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a movie entity in the database.
 * <p>
 * This class is mapped to the "movies" table and contains details about the
 * movie, including its title, director, type, countries of origin, and release
 * year. It uses Lombok annotations for boilerplate code reduction, such as
 * getters, setters, and constructors.
 * </p>
 */
@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "type")
@EqualsAndHashCode
public class MovieEntity {

	/**
	 * The unique identifier for the movie.
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * The title of the movie. This field is mandatory.
	 */
	@Column(nullable = false, unique = true)
	private String title;

	/**
	 * The director of the movie. This field is optional.
	 */
	@Column(nullable = true)
	private String director;

	/**
	 * The type of the movie. This field is a many-to-one relationship with
	 * {@link TypeEntity}.
	 */
	@ManyToOne
	@JoinColumn(name = "type_id")
	private TypeEntity type;

	/**
	 * The countries where the movie was produced. This field is optional.
	 */
	@Column(nullable = true)
	private String countries;

	/**
	 * The release year of the movie. This field is optional.
	 */
	@Column(name = "release_year", nullable = true)
	private Integer releaseYear;
	
	public Integer getTypeId() {
        return type != null ? type.getTypeId() : null;
    }

}
