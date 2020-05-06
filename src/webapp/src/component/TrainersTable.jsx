import React, {useState} from 'react';
import 'react-toastify/dist/ReactToastify.css';
import {AddTrainerModal} from "./AddTrainerModal";

export const TrainersTable = props => {

    const {trainers, refreshWorkoutsOfTrainer, selectedTrainer, refreshTrainers} = props;

    const [showAddTrainer, setShowAddTrainer] = useState(false);

    return (
        <div className="col-sm">

            <AddTrainerModal
                show={showAddTrainer}
                onHide={() => {
                    setShowAddTrainer(false);
                    refreshTrainers();
                }}
            />

            <h3>Trainers</h3>
            <table className="table">
                <thead>
                <tr>
                    <th>Trainer Name</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {
                    trainers.map(trainer => {
                        return <tr key={trainer.id}
                                   style={selectedTrainer && selectedTrainer.id === trainer.id ? {backgroundColor: "rgba(0,0,0,.05)"} : {}}>
                            <td>{trainer.name}</td>
                            <td>
                                <button className="btn btn-primary btn-sm"
                                        onClick={() => refreshWorkoutsOfTrainer(trainer)}>
                                    Show Workouts
                                </button>
                            </td>
                        </tr>
                    })
                }
                </tbody>
            </table>
            <div className="row">
                <button className="btn btn-success" onClick={() => setShowAddTrainer(true)}>Add Trainer</button>
            </div>
        </div>
    )
};