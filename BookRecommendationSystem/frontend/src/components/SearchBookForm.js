import React, { useState } from "react";
import axios from "axios";
import { Form, FormGroup, Label, Input, Button } from "reactstrap";

const backendUrl = 'https://bookrecommendationsystem-1.onrender.com';

const initialFilters = {
  book_id: "",
  title: "",
  year: "",
  pages: "",
  description: "",
  genres: "",
  average_rating: "",
  ratings_count: "",
  authors: "",
  average_rating_min: "",
  average_rating_max: "",
};

const SearchBookForm = ({ onSearchResults }) => {
  const [filters, setFilters] = useState({
    book_id: "",
    title: "",
    year: "",
    pages: "",
    description: "",
    genres: "",
    average_rating: "",
    ratings_count: "",
    authors: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFilters({ ...filters, [name]: value });
  };
  const handleClear = () => {
    setFilters(initialFilters); 
  };
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.get(`${backendUrl}/api/BookRec/filter/`, {
        params: filters,
      });

      if (response.status === 200) {
        onSearchResults(response.data); 
      }
    } catch (error) {
      console.error("Error fetching filtered books:", error);
    }
  };

  return (
    <Form onSubmit={handleSubmit}>
      <FormGroup>
        <Label for="book_id">Book ID</Label>
        <Input
          type="text"
          name="book_id"
          value={filters.book_id}
          onChange={handleInputChange}
          placeholder="Enter book ID"
        />
      </FormGroup>

      <FormGroup>
        <Label for="title">Title</Label>
        <Input
          type="text"
          name="title"
          value={filters.title}
          onChange={handleInputChange}
          placeholder="Enter book title"
        />
      </FormGroup>

      <FormGroup>
        <Label for="authors">Authors</Label>
        <Input
          type="text"
          name="authors"
          value={filters.authors}
          onChange={handleInputChange}
          placeholder="Enter authors"
        />
      </FormGroup>

      <FormGroup>
        <Label for="year">Year</Label>
        <Input
          type="number"
          name="year"
          value={filters.year}
          onChange={handleInputChange}
          placeholder="Enter publication year"
        />
      </FormGroup>

      <FormGroup>
        <Label for="pages">Pages</Label>
        <Input
          type="number"
          name="pages"
          value={filters.pages}
          onChange={handleInputChange}
          placeholder="Enter pages"
        />
      </FormGroup>

      <FormGroup>
        <Label for="genres">Genres</Label>
        <Input
          type="text"
          name="genres"
          value={filters.genres}
          onChange={handleInputChange}
          placeholder="Enter genres"
        />
      </FormGroup>
      <FormGroup>
        <Label for="average_rating_min">Average Rating (Min)</Label>
        <Input
          type="number"
          name="average_rating_min"
          value={filters.average_rating_min}
          onChange={handleInputChange}
          placeholder="Enter minimum average rating"
        />
      </FormGroup>

      <FormGroup>
        <Label for="average_rating_max">Average Rating (Max)</Label>
        <Input
          type="number"
          name="average_rating_max"
          value={filters.average_rating_max}
          onChange={handleInputChange}
          placeholder="Enter maximum average rating"
        />
      </FormGroup>
    
      <Button color="primary" type="submit">
        Search
      </Button>
      <Button style={{ marginLeft: "10px" }} color="primary" type="button" onClick={handleClear}>
        Clear Search
      </Button>
    </Form>
  );
};

export default SearchBookForm;
