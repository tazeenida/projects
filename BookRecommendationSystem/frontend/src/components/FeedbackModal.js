import React from "react";
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from "reactstrap";

const FeedbackModal = ({ isOpen, toggle, message, title }) => {
  return (
    <Modal isOpen={isOpen} toggle={toggle}>
      <ModalHeader toggle={toggle}>{title}</ModalHeader>
      <ModalBody>{message}</ModalBody>
      <ModalFooter>
        <Button color="primary" onClick={toggle}>OK</Button>
      </ModalFooter>
    </Modal>
  );
};

export default FeedbackModal;
