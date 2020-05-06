import React, {useEffect, useState} from 'react';
import {TrainersTable} from "./TrainersTable";
import {ToastContainer} from "react-toastify";
import {WorkoutsTable} from "./WorkoutsTable";
import {getAllTrainers, getWorkoutsOfTrainer} from "../service/ApiService";

export const SudorApp = props => {

    const [trainers, setTrainers] = useState([]);
    const [selectedTrainer, setSelectedTrainer] = useState();

    const refreshTrainers = () => {
        getAllTrainers().then(resp => {
            setTrainers(resp.data);
        })
    };

    const refreshWorkoutsOfTrainer = (trainer) => {
        getWorkoutsOfTrainer(trainer).then(resp => {
            setSelectedTrainer({
                ...trainer,
                workouts: resp.data
            })
        })
    };

    useEffect(() => {
        refreshTrainers();
    }, []);

    return (
        <>
            <ToastContainer/>
            <h1>Sudor Application</h1>
            <div className="container">
                <div className="row">
                    <TrainersTable trainers={trainers} refreshWorkoutsOfTrainer={refreshWorkoutsOfTrainer}
                                   refreshTrainers={refreshTrainers} selectedTrainer={selectedTrainer}/>
                    <WorkoutsTable trainer={selectedTrainer} refreshWorkoutsOfTrainer={refreshWorkoutsOfTrainer}/>
                </div>
            </div>
        </>
    )
};