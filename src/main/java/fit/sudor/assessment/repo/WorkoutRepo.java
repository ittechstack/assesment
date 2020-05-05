package fit.sudor.assessment.repo;

import fit.sudor.assessment.domain.Trainer;
import fit.sudor.assessment.domain.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepo extends JpaRepository<Workout, Long> {

    List<Workout> findByTrainer(Trainer trainer);
}
