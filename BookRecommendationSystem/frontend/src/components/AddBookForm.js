import React, { useState } from "react";
import axios from "axios";
import { Form, FormGroup, Label, Input, Button } from "reactstrap";
import { v4 as uuidv4 } from 'uuid'; 
import FeedbackModal from "./FeedbackModal"; 

const backendUrl = 'https://bookrecommendationsystem-1.onrender.com';

const AddBookForm = ({ onBookAdded }) => {
  const [bookData, setBookData] = useState({
    book_id: uuidv4(),
    title: "",
    year: "",
    pages: "",
    description: "",
    genres: "",
    average_rating: "",
    ratings_count: "",
    authors: "",
  });
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [modalTitle, setModalTitle] = useState("");
  const [modalMessage, setModalMessage] = useState("");
  const toggleModal = () => setIsModalOpen(!isModalOpen);
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setBookData({ ...bookData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(`${backendUrl}/api/BookRec/add/`, bookData);
      if (response.status === 201) { 
        setModalTitle("Book Added"); 
        setModalMessage(`"${bookData.title}" was successfully added.`); 
        toggleModal(); 
        setTimeout(() => {
          window.location.reload(); 
        }, 2000);
        if (onBookAdded) {
          onBookAdded(); 
        }
      } else {
        setModalTitle("Add Book Failed");
        setModalMessage("Failed to add the book.");
        toggleModal();
      }
    } catch (error) {
      setModalTitle("Error Adding Book");
      setModalMessage("An error occurred while adding the book. Please try again.");
      toggleModal();
      console.error("Error adding book:", error.response?.data);
    }
  };
  return (
    <>
    <h2>Add Books</h2>
    <Form onSubmit={handleSubmit}>
      <FormGroup>
        <Label for="bookId">Book ID</Label>
        <Input
          type="text"
          name="book_id"
          value={bookData.book_id}
          onChange={handleInputChange}
          readOnly
          placeholder="Book ID (auto-generated)"
        />
      </FormGroup>
      <FormGroup>
        <Label for="bookTitle">Book Title</Label>
        <Input
          type="text"
          name="title"
          value={bookData.title}
          onChange={handleInputChange}
          placeholder="Enter book title"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookAuthor">Authors</Label>
        <Input
          type="text"
          name="authors"
          value={bookData.authors}
          onChange={handleInputChange}
          placeholder="Enter authors"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookYear">Year</Label>
        <Input
          type="number"
          name="year"
          value={bookData.year}
          onChange={handleInputChange}
          placeholder="Enter book year"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookPages">Pages</Label>
        <Input
          type="number"
          name="pages"
          value={bookData.pages}
          onChange={handleInputChange}
          placeholder="Enter book pages"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookDescription">Description</Label>
        <Input
          type="text"
          name="description"
          value={bookData.description}
          onChange={handleInputChange}
          placeholder="Enter description"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookGenres">Genres</Label>
        <Input
          type="text"
          name="genres"
          value={bookData.genres}
          onChange={handleInputChange}
          placeholder="Enter genres"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookAvgRating">Average Rating</Label>
        <Input
          type="number"
          name="average_rating"
          value={bookData.average_rating}
          onChange={handleInputChange}
          placeholder="Enter average rating"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookRatingsCount">Ratings Count</Label>
        <Input
          type="number"
          name="ratings_count"
          value={bookData.ratings_count}
          onChange={handleInputChange}
          placeholder="Enter ratings count"
        />
      </FormGroup>

      <Button color="primary" type="submit">
        Add Book
      </Button>
    </Form>
    <FeedbackModal
    isOpen={isModalOpen}
    toggle={toggleModal}
    title={modalTitle}
    message={modalMessage}
  />
  </>
  );
};

export default AddBookForm;
