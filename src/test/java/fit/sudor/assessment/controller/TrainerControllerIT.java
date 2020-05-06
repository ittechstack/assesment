package fit.sudor.assessment.controller;

import fit.sudor.assessment.dto.TrainerDto;
import fit.sudor.assessment.dto.WorkoutDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * Integration tests for TrainerController methods
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainerControllerIT {

    @Autowired
    private TestRestTemplate rt;

    @Test
    public void get_all_trainers_returns_results() {
        ResponseEntity<List<TrainerDto>> response = rt.exchange("/trainers", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        List<TrainerDto> trainer = response.getBody();
        Assertions.assertNotNull(trainer);
        Assertions.assertFalse(trainer.isEmpty());
    }

    @Test
    public void save_trainer_name_cannot_be_null() {
        TrainerDto trainer = new TrainerDto();
        ResponseEntity<TrainerDto> response = rt.postForEntity("/trainers", trainer, TrainerDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void save_trainer_can_not_add_already_used_name() {
        TrainerDto trainer = new TrainerDto();
        trainer.setName("Jack");
        ResponseEntity<TrainerDto> response = rt.postForEntity("/trainers", trainer, TrainerDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void save_trainer_success() {
        TrainerDto trainer = new TrainerDto();
        trainer.setName("Test Trainer");
        ResponseEntity<TrainerDto> response = rt.postForEntity("/trainers", trainer, TrainerDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getId());
    }

    @Test
    public void get_workouts_by_trainer_should_have_trainer() {
        ResponseEntity<Object> response = rt.getForEntity("/trainers/123/workouts", Object.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void get_workouts_by_trainer_success() {
        ResponseEntity<List<WorkoutDto>> response = rt.exchange("/trainers/1000/workouts", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        List<WorkoutDto> workouts = response.getBody();

        Assertions.assertNotNull(workouts);
        Assertions.assertFalse(workouts.isEmpty());

    }

}
