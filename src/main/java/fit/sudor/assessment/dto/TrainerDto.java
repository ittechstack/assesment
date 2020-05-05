package fit.sudor.assessment.dto;

import fit.sudor.assessment.domain.Trainer;

public class TrainerDto {

    private Long id;
    private String name;

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

    public static TrainerDto from(Trainer trainer) {
        TrainerDto dto = new TrainerDto();
        dto.setId(trainer.getId());
        dto.setName(trainer.getName());
        return dto;
    }

    public Trainer toTrainer() {
        Trainer trainer = new Trainer();
        trainer.setId(this.getId());
        trainer.setName(this.getName());
        return trainer;
    }
}
