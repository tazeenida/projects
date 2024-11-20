import { Modal, ModalHeader, ModalBody, Button } from "reactstrap";

const FeatureModal = ({ activeItem, toggle, isOpen }) => {
  return (
    <Modal className="popup" isOpen={isOpen} toggle={toggle}> 
    <div className="popup-container">
      <div className="popup">
      <ModalHeader>
          <h1>{activeItem.Title}</h1>
      </ModalHeader>
      <ModalBody>
      <div className="officer">
        <img className="event-images" src={activeItem.ImageUrl} alt="Event" />
        <div className="event-title">{activeItem.Title}</div>
        <div className="event-date">{activeItem.Date}</div>
        <div className="event-description">{activeItem.Description}</div>
        <div className="event-time">{activeItem.StartTime} - {activeItem.EndTime}</div>
        <div className="event-location">{activeItem.Location}</div>
      </div>
      </ModalBody>
      <Button onClick={toggle}>Close</Button>
      </div>
      </div>
    </Modal>
  );
};

export default FeatureModal;
