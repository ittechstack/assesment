import axios from 'axios'
import {toast} from "react-toastify";

const API_URL = 'http://localhost:8080';
const TRAINER_API_URL = `${API_URL}/trainers`;
const WORKOUT_API_URL = `${API_URL}/workouts`;

export const getAllTrainers = () => {
    return axios.get(TRAINER_API_URL);
};

export const getWorkoutsOfTrainer = (trainer) => {
    return axios.get(`${TRAINER_API_URL}/${trainer.id}/workouts`);
};

export const addTrainer = (trainer) => {
    return axios.post(TRAINER_API_URL, trainer);
};

export const addWorkout = (workout) => {
    return axios.post(WORKOUT_API_URL, workout);
};

axios.interceptors.response.use(function (response) {
    if (response.status !== 200) {
        toast.success('The process is completed successfully', {position: "top-center"})
    }
    return response;
}, function (error) {
    toast.error(error.response.data.message, {position: "top-center"})
});
