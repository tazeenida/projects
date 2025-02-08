import React, { useState } from "react";
import axios from "axios";
import { Form, FormGroup, Label, Input, Button } from "reactstrap";
import FeedbackModal from "./FeedbackModal"; 
function UpdateBookForm({ onBookUpdated }) {
  const [bookData, setBookData] = useState({
    book_id: '', 
    title: '',
    year: '',
    pages: '',
    description: '',
    genres: '',
    average_rating: '',
    ratings_count: '',
    authors: ''
  });

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [modalTitle, setModalTitle] = useState("");
  const [modalMessage, setModalMessage] = useState("");
  const toggleModal = () => setIsModalOpen(!isModalOpen);

  const backendUrl = 'https://bookrecommendationsystem-1.onrender.com';

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBookData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault(); 
    try {
      const response = await axios.put(`${backendUrl}/api/BookRec/update/`, bookData);
      if (response.status === 200) {
        const bookTitle = bookData.title ? `"${bookData.title}" was successfully updated.` : "Update Successful.";
        setModalTitle("Book Updated"); 
        setModalMessage(bookTitle); 
        toggleModal();
        setTimeout(() => {
          window.location.reload();
        }, 2000);
      }else {
        setModalTitle("Update Failed");
        setModalMessage("Failed to update the book.");
        toggleModal();
      }
    } catch (error) {
      setModalTitle("Error updating Book");
      setModalMessage("An error occurred while updating the book. Please try again.");
      toggleModal();
      console.error("Error updating book:", error.response?.data);
    }
  };
  

  return (
    <div>
      <h2>Update Book</h2>
      <Form onSubmit={handleSubmit}>
        <FormGroup>
          <Label>Book ID:</Label>
          <Input
            type="text"
            name="book_id"
            value={bookData.book_id}
            onChange={handleChange}
            required
            placeholder="Enter book id"
          />
        </FormGroup>
        <FormGroup>
          <Label>Title:</Label>
          <Input
            type="text"
            name="title"
            value={bookData.title}
            onChange={handleChange}
            placeholder="Enter title"
          />
        </FormGroup>
        <FormGroup>
        <Label for="bookAuthor">Authors</Label>
        <Input
          type="text"
          name="authors"
          value={bookData.authors}
          onChange={handleChange}
          placeholder="Enter authors"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookYear">Year</Label>
        <Input
          type="number"
          name="year"
          value={bookData.year}
          onChange={handleChange}
          placeholder="Enter book year"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookPages">Pages</Label>
        <Input
          type="number"
          name="pages"
          value={bookData.pages}
          onChange={handleChange}
          placeholder="Enter book pages"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookDescription">Description</Label>
        <Input
          type="text"
          name="description"
          value={bookData.description}
          onChange={handleChange}
          placeholder="Enter description"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookGenres">Genres</Label>
        <Input
          type="text"
          name="genres"
          value={bookData.genres}
          onChange={handleChange}
          placeholder="Enter genres"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookAvgRating">Average Rating</Label>
        <Input
          type="number"
          name="average_rating"
          value={bookData.average_rating}
          onChange={handleChange}
          placeholder="Enter average rating"
        />
      </FormGroup>

      <FormGroup>
        <Label for="bookRatingsCount">Ratings Count</Label>
        <Input
          type="number"
          name="ratings_count"
          value={bookData.ratings_count}
          onChange={handleChange}
          placeholder="Enter ratings count"
        />
      </FormGroup>
        <Button color="primary" type="submit">Update</Button>
          </Form>
          <FeedbackModal
        isOpen={isModalOpen}
        toggle={toggleModal}
        title={modalTitle}
        message={modalMessage}
      />
    </div>
  );
}

export default UpdateBookForm;
