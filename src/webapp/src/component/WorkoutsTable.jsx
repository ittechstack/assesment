import React, {useState} from 'react';
import 'react-toastify/dist/ReactToastify.css';
import {AddWorkoutModal} from "./AddWorkoutModal";

export const WorkoutsTable = props => {

    const {trainer, refreshWorkoutsOfTrainer} = props;

    const [showAddWorkout, setShowAddWorkout] = useState(false);

    return (
        <div className="col-sm">
            <AddWorkoutModal trainer={trainer}
                             show={showAddWorkout}
                             onHide={() => {
                                 setShowAddWorkout(false);
                                 refreshWorkoutsOfTrainer(trainer);
                             }}
            />

            {
                trainer &&
                <>
                    <h3>Workouts of <span style={{color: "red"}}>{trainer.name}</span></h3>
                    <table className="table">
                        <thead>
                        <tr>
                            <th>Workout Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            trainer.workouts.map(workout => {
                                return <tr key={workout.id}>
                                    <td>{workout.name}</td>
                                </tr>
                            })
                        }
                        </tbody>
                    </table>
                    <div className="d-flex flex-row-reverse">
                        <button className="btn btn-success" onClick={() => setShowAddWorkout(true)}>Add Workout</button>
                    </div>
                </>
            }
        </div>
    )
};