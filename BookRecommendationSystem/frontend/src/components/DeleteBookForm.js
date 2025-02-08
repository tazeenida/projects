import axios from "axios";
import React, { useState } from "react";
import { Form, FormGroup, Label, Input, Button } from "reactstrap";
import FeedbackModal from "./FeedbackModal";

const backendUrl = 'https://bookrecommendationsystem-1.onrender.com';

const DeleteBookForm = () => {
  const [bookTitle, setBookTitle] = useState(""); 
  const [bookAuthors, setBookAuthors] = useState(""); 
  
  const [isModalOpen, setIsModalOpen] = useState(false); 
  const [modalTitle, setModalTitle] = useState(""); 
  const [modalMessage, setModalMessage] = useState(""); 
  const toggleModal = () => setIsModalOpen(!isModalOpen);

  const handleSubmit = async (e) => {
    e.preventDefault(); 

    try {
      const response = await axios.delete(`${backendUrl}/api/BookRec/delete/`, {
        data: { title: bookTitle, authors: bookAuthors },
      });

      if (response.status === 200) { 
        setModalTitle("Book Deleted"); 
        setModalMessage(`"${bookTitle}" was successfully deleted.`); 
        toggleModal(); 
        
        setTimeout(() => {
          window.location.reload(); 
        }, 2000); 
      } else {
        setModalTitle("Delete Book Failed");
        setModalMessage("Failed to delete the book.");
        toggleModal();
      }
    } catch (error) {
      setModalTitle("Error Deleting Book");
      setModalMessage("An error occurred while deleting the book. Please try again.");
      toggleModal();
    }
  };

  return (
    <>
      <h2>Delete Books</h2>
      <Form onSubmit={handleSubmit}>
        <FormGroup>
          <Label for="deleteTitle">Book Title</Label>
          <Input
            type="text"
            name="title"
            value={bookTitle}
            onChange={(e) => setBookTitle(e.target.value)} 
            placeholder="Enter book title"
          />
        </FormGroup>
        <FormGroup>
          <Label for="deleteAuthors">Book Authors</Label>
          <Input
            type="text"
            name="authors"
            value={bookAuthors}
            onChange={(e) => setBookAuthors(e.target.value)} 
            placeholder="Enter book authors"
          />
        </FormGroup>
        <Button color="danger" type="submit">
          Delete Book
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

export default DeleteBookForm;
