package fit.sudor.assessment;

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

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorkoutControllerTest {

    @Autowired
    private TestRestTemplate rt;

    @Test
    public void save_workout_name_cannot_be_null() {
        WorkoutDto workout = new WorkoutDto();
        ResponseEntity<WorkoutDto> response = rt.postForEntity("/workouts", workout, WorkoutDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void save_success() {
        WorkoutDto workout = new WorkoutDto();
        workout.setName("Unit Test Workout");
        ResponseEntity<WorkoutDto> response = rt.postForEntity("/workouts", workout, WorkoutDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getId());
    }

}
