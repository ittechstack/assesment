package fit.sudor.assessment.service.impl;

import fit.sudor.assessment.domain.Trainer;
import fit.sudor.assessment.domain.Workout;
import fit.sudor.assessment.exception.TrainerNotFoundException;
import fit.sudor.assessment.repo.TrainerRepo;
import fit.sudor.assessment.repo.WorkoutRepo;
import fit.sudor.assessment.service.WorkoutService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private WorkoutRepo workoutRepo;
    private TrainerRepo trainerRepo;

    public WorkoutServiceImpl(WorkoutRepo workoutRepo, TrainerRepo trainerRepo) {
        this.workoutRepo = workoutRepo;
        this.trainerRepo = trainerRepo;
    }

    @Override
    public Workout save(Workout workout) {
        return workoutRepo.save(workout);
    }

    @Override
    public List<Workout> getByTrainer(Trainer trainer) {
        Optional<Trainer> optional = trainerRepo.findById(trainer.getId());

        Trainer trainerFromDb = optional.orElseThrow(TrainerNotFoundException::new);

        return workoutRepo.findByTrainer(trainerFromDb);
    }
}
