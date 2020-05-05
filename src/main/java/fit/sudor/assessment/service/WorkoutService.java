package fit.sudor.assessment.service;

import fit.sudor.assessment.domain.Trainer;
import fit.sudor.assessment.domain.Workout;

import java.util.List;

public interface WorkoutService {

    Workout save(Workout workout);

    List<Workout> getByTrainer(Trainer trainer);
}
