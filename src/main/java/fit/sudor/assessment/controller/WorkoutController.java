package fit.sudor.assessment.controller;

import fit.sudor.assessment.domain.Workout;
import fit.sudor.assessment.dto.WorkoutDto;
import fit.sudor.assessment.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Workout workout = workoutService.save(workoutDto.toWorkout());

        return new ResponseEntity<>(WorkoutDto.from(workout), HttpStatus.CREATED);
    }
}
