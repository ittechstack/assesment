import {addWorkout} from "../service/ApiService";
import Modal from "react-bootstrap/Modal";
import {Button, Form} from "react-bootstrap";
import React from "react";

export const AddWorkoutModal = (props) => {

    const {trainer} = props;

    const handleSubmit = (event) => {
        event.preventDefault();
        event.stopPropagation();
        const form = event.currentTarget;

        const workout = {
            name: form.name.value,
            trainer: {
                id: trainer.id
            }
        };

        addWorkout(workout).then(resp => {
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
                    Add New Workout for <span style={{color: "red"}}>{trainer && trainer.name}</span>
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="name">
                        <Form.Label>Workout Name</Form.Label>
                        <Form.Control required type="text" placeholder="Workout name"/>
                        <Form.Text className="text-muted">
                            Please write name of workout
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
