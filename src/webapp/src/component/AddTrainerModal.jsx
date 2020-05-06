import {addTrainer} from "../service/ApiService";
import Modal from "react-bootstrap/Modal";
import {Button, Form} from "react-bootstrap";
import React from "react";

export const AddTrainerModal = (props) => {

    const handleSubmit = (event) => {
        event.preventDefault();
        event.stopPropagation();
        const form = event.currentTarget;

        const trainer = {
            name: form.name.value
        };

        addTrainer(trainer).then(resp => {
            props.onHide();
        });
    };

    return (
        <Modal
            {...props}
            size="md"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Add New Trainer
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="name">
                        <Form.Label>Trainer Name</Form.Label>
                        <Form.Control required type="text" placeholder="Full name"/>
                        <Form.Text className="text-muted">
                            Please write your full name
                        </Form.Text>
                    </Form.Group>

                    <Modal.Footer>
                        <Button variant="secondary" onClick={props.onHide}>Close</Button>
                        <Button variant="primary" type="submit">Submit</Button>
                    </Modal.Footer>
                </Form>
            </Modal.Body>
        </Modal>
    );
};
