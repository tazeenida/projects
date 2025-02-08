import { Modal, ModalHeader, ModalBody, Button } from "reactstrap";

const BookModal = ({ activeItem, toggle, isOpen }) => {
  return (
    <Modal isOpen={isOpen} toggle={toggle} className="bookDetails"> 
      <ModalHeader toggle={toggle} className="mb-3">
        Book Details
      </ModalHeader>
      <ModalBody>
      <div className="mb-3">
          <strong>Book ID:</strong> {activeItem.book_id}
        </div>
        <div className="mb-3">
          <strong>Title:</strong> {activeItem.title}
        </div>
        <div className="mb-3">
          <strong>Authors:</strong> {activeItem.authors}
        </div>
        <div className="mb-3">
          <strong>Year:</strong> {activeItem.year}
        </div>
        <div className="mb-3">
          <strong>Pages:</strong> {activeItem.pages}
        </div>
        <div className="mb-3">
          <strong>Description:</strong> {activeItem.description}
        </div>
        <div className="mb-3">
          <strong>Genres:</strong> {activeItem.genres}
        </div>
        <div className="mb-3">
          <strong>Average Rating:</strong> {activeItem.average_rating}
        </div>
        <div>
          <strong>Ratings Count:</strong> {activeItem.ratings_count}
        </div>
      </ModalBody>
      <div className="text-right p-3">
        <Button color="secondary" onClick={toggle}>
          Close
        </Button>
      </div>
    </Modal>
  );
};

export default BookModal;
