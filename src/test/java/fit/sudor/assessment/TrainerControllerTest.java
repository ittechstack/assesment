package fit.sudor.assessment;

import fit.sudor.assessment.domain.Workout;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainerControllerTest {

    @Autowired
    private TestRestTemplate rt;

    @Test
    public void save_trainer_name_cannot_be_null() {
        TrainerDto trainer = new TrainerDto();
        ResponseEntity<TrainerDto> response = rt.postForEntity("/trainers", trainer, TrainerDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void save_success() {
        TrainerDto trainer = new TrainerDto();
        trainer.setName("Test Trainer");
        ResponseEntity<TrainerDto> response = rt.postForEntity("/trainers", trainer, TrainerDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getId());
    }

    @Test
    public void get_workouts_by_trainer_should_have_trainer() {
        ResponseEntity<List<WorkoutDto>> response = rt.exchange("/trainers/123/workouts", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void get_workouts_by_trainer_success() {
        ResponseEntity<List<WorkoutDto>> response = rt.exchange("/trainers/123/workouts", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        List<WorkoutDto> workouts = response.getBody();


    }

}
