package fit.sudor.assessment.controller;

import fit.sudor.assessment.dto.TrainerDto;
import fit.sudor.assessment.dto.WorkoutDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * integration tests for WorkoutController methods
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorkoutControllerIT {

    @Autowired
    private TestRestTemplate rt;

    @Test
    public void save_workout_name_cannot_be_null() {
        WorkoutDto workout = new WorkoutDto();
        ResponseEntity<WorkoutDto> response = rt.postForEntity("/workouts", workout, WorkoutDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void save_workout_should_have_a_trainer() {

        WorkoutDto workout = new WorkoutDto();
        workout.setName("Unit Test Workout");

        ResponseEntity<WorkoutDto> response = rt.postForEntity("/workouts", workout, WorkoutDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void save_workout_should_have_a_valid_trainer() {
        TrainerDto trainer = new TrainerDto();
        trainer.setId(123L);

        WorkoutDto workout = new WorkoutDto();
        workout.setName("Unit Test Workout");
        workout.setTrainer(trainer);

        ResponseEntity<WorkoutDto> response = rt.postForEntity("/workouts", workout, WorkoutDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void save_workout_can_not_add_already_used_name() {
        TrainerDto trainer = new TrainerDto();
        trainer.setId(1000L);

        WorkoutDto workout = new WorkoutDto();
        workout.setName("Test Workout 1");
        workout.setTrainer(trainer);

        ResponseEntity<WorkoutDto> response = rt.postForEntity("/workouts", workout, WorkoutDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void save_success() {
        TrainerDto trainer = new TrainerDto();
        trainer.setId(1000L);

        WorkoutDto workout = new WorkoutDto();
        workout.setName("Unit Test Workout");
        workout.setTrainer(trainer);

        ResponseEntity<WorkoutDto> response = rt.postForEntity("/workouts", workout, WorkoutDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getId());
    }
}
