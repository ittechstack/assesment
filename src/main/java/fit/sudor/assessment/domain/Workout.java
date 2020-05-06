package fit.sudor.assessment.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "WORKOUT", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"TRAINER_ID", "NAME"})
})
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

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

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return id.equals(workout.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
