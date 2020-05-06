package fit.sudor.assessment.controller;

import fit.sudor.assessment.domain.Trainer;
import fit.sudor.assessment.domain.Workout;
import fit.sudor.assessment.dto.TrainerDto;
import fit.sudor.assessment.dto.WorkoutDto;
import fit.sudor.assessment.service.TrainerService;
import fit.sudor.assessment.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private TrainerService trainerService;
    private WorkoutService workoutService;

    public TrainerController(TrainerService trainerService, WorkoutService workoutService) {
        this.trainerService = trainerService;
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<TrainerDto> getAll() {
        return trainerService.getAll()
                .stream()
                .map(TrainerDto::from)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<TrainerDto> saveTrainer(@RequestBody TrainerDto trainerDto) {
        if (trainerDto.getName() == null || trainerDto.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name field can not be null");
        }

        Trainer trainer = trainerService.save(trainerDto.toTrainer());

        return new ResponseEntity<>(TrainerDto.from(trainer), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/workouts")
    public List<WorkoutDto> getWorkoutsByTrainerId(@PathVariable("id") Long id) {
        Trainer trainer = new Trainer();
        trainer.setId(id);

        List<Workout> workouts = workoutService.getByTrainer(trainer);

        List<WorkoutDto> workoutDtoList = workouts.stream()
                .map(WorkoutDto::from)
                .collect(Collectors.toList());

        return workoutDtoList;
    }
}
