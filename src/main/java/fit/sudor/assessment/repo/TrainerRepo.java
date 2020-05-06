package fit.sudor.assessment.repo;

import fit.sudor.assessment.domain.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepo extends JpaRepository<Trainer, Long> {

    Trainer findFirstByNameIgnoreCase(String name);
}
