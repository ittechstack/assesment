package fit.sudor.assessment.dto;

import fit.sudor.assessment.domain.Workout;

public class WorkoutDto {

    private Long id;
    private String name;
    private TrainerDto trainer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static WorkoutDto from(Workout workout) {
        WorkoutDto dto = new WorkoutDto();
        dto.setId(workout.getId());
        dto.setName(workout.getName());
        return dto;
    }

    public Workout toWorkout() {
        Workout workout = new Workout();
        workout.setId(this.getId());
        workout.setName(this.getName());
        workout.setTrainer(this.getTrainer().toTrainer());
        return workout;
    }

    public TrainerDto getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerDto trainer) {
        this.trainer = trainer;
    }
}
