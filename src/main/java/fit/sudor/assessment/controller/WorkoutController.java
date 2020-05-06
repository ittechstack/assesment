package fit.sudor.assessment.controller;

import fit.sudor.assessment.domain.Workout;
import fit.sudor.assessment.dto.TrainerDto;
import fit.sudor.assessment.dto.WorkoutDto;
import fit.sudor.assessment.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    private WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<WorkoutDto> saveWorkout(@RequestBody WorkoutDto workoutDto) {
        if (workoutDto.getName() == null || workoutDto.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name field can not be null");
        }

        TrainerDto trainerDto = workoutDto.getTrainer();
        if (trainerDto == null || trainerDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "trainer  id field can not be null");
        }

        Workout workout = workoutService.save(workoutDto.toWorkout());

        return new ResponseEntity<>(WorkoutDto.from(workout), HttpStatus.CREATED);
    }
}
