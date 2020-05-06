package fit.sudor.assessment.service.impl;

import fit.sudor.assessment.domain.Trainer;
import fit.sudor.assessment.domain.Workout;
import fit.sudor.assessment.exception.SameNameException;
import fit.sudor.assessment.exception.TrainerNotFoundException;
import fit.sudor.assessment.repo.TrainerRepo;
import fit.sudor.assessment.repo.WorkoutRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WorkoutServiceImplTest {

    @MockBean
    private TrainerRepo trainerRepo;

    @MockBean
    private WorkoutRepo workoutRepo;

    @Autowired
    private WorkoutServiceImpl workoutService;

    @Test
    void save_throws_exception_if_trainer_not_exists() {
        Mockito.when(trainerRepo.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(TrainerNotFoundException.class, () -> {
            Workout workout = new Workout();
            workout.setTrainer(new Trainer());
            workoutService.save(workout);
        });
    }

    @Test
    void save_throws_exception_if_name_already_used() {
        Mockito.when(trainerRepo.findById(Mockito.any())).thenReturn(Optional.of(new Trainer()));
        Mockito.when(workoutRepo.findFirstByTrainerAndNameIgnoreCase(Mockito.any(), Mockito.any())).thenReturn(new Workout());

        Workout workout = new Workout();
        workout.setTrainer(new Trainer());

        Assertions.assertThrows(SameNameException.class, () -> workoutService.save(workout));
    }

    @Test
    void save_success() {
        Mockito.when(trainerRepo.findById(Mockito.any())).thenReturn(Optional.of(new Trainer()));
        Mockito.when(workoutRepo.findFirstByTrainerAndNameIgnoreCase(Mockito.any(), Mockito.any())).thenReturn(null);

        Workout workout = new Workout();
        workout.setId(1L);
        workout.setTrainer(new Trainer());

        Mockito.when(workoutRepo.save(Mockito.any())).thenReturn(workout);

        Workout result = workoutService.save(workout);

        Assertions.assertEquals(result.getId(), workout.getId());
    }

    @Test
    void get_by_trainer_throws_exception_if_trainer_not_exists() {
        Mockito.when(trainerRepo.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(TrainerNotFoundException.class, () -> {
            workoutService.getByTrainer(new Trainer());
        });
    }

    @Test
    void get_by_trainer_success() {
        Mockito.when(trainerRepo.findById(Mockito.any())).thenReturn(Optional.of(new Trainer()));
        Mockito.when(workoutRepo.findByTrainer(Mockito.any())).thenReturn(List.of(new Workout()));

        List<Workout> result = workoutService.getByTrainer(new Trainer());

        Assertions.assertFalse(result.isEmpty());
    }
}