import React, { useState } from "react";
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from "reactstrap";
import SearchBookForm from "./SearchBookForm";
import BookList from "./BookList";

const BookSearchPage = () => {
  const [searchResults, setSearchResults] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const toggleModal = () => {
    setIsModalOpen(!isModalOpen); 
  };

  const handleSearchResults = (results) => {
    setSearchResults(results);
    toggleModal(); 
  };

  return (
    <div>
      <h2>Search Books</h2>
      <SearchBookForm onSearchResults={handleSearchResults} />
      
      <Modal isOpen={isModalOpen} toggle={toggleModal}>
        <ModalHeader toggle={toggleModal}>Search Results</ModalHeader>
        <ModalBody>
          {searchResults.length > 0 ? (
            <BookList books={searchResults} />
          ) : (
            <p>No results found.</p>
          )}
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={toggleModal}>
            Close
          </Button>
        </ModalFooter>
      </Modal>
    </div>
  );
};

export default BookSearchPage;
