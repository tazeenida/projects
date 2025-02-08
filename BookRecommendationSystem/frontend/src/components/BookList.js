import React from "react";
import { ListGroup, ListGroupItem } from "reactstrap";

const BookList = ({ books }) => {
  return (
    <ListGroup>
      {books.map((book) => (
        <ListGroupItem key={book.book_id}>
          {book.title} by {book.authors} (Published: {book.year})
        </ListGroupItem>
      ))}
    </ListGroup>
  );
};

export default BookList;
