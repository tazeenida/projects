import { Modal, ModalHeader, ModalBody, Button } from "reactstrap";

const ClubModal = ({ activeItem, toggle, isOpen }) => {
  return (
    <Modal className="popup" isOpen={isOpen}> 
    <div className="popup-container">
      <div className="popup">
      <ModalHeader>
          <h1>{activeItem.Title}</h1>
      </ModalHeader>
      <ModalBody>
      <div className="officer">
        <p>President: {activeItem.PresidentName}</p>
        <p>Treasurer: {activeItem.TreasurerName}</p>
        <p>AdvisorName: {activeItem.AdvisorName}</p>
        <p>Email: {activeItem.Email}</p>
      </div>
      </ModalBody>
      <Button onClick={toggle}>Close</Button>
      </div>
      </div>
    </Modal>
  );
};

export default ClubModal;
