package fit.sudor.assessment.service.impl;

import fit.sudor.assessment.domain.Trainer;
import fit.sudor.assessment.exception.SameNameException;
import fit.sudor.assessment.repo.TrainerRepo;
import fit.sudor.assessment.service.TrainerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private TrainerRepo trainerRepo;

    public TrainerServiceImpl(TrainerRepo trainerRepo) {
        this.trainerRepo = trainerRepo;
    }

    @Override
    public Trainer save(Trainer trainer) {
        Trainer trainerWithSameName = trainerRepo.findFirstByNameIgnoreCase(trainer.getName());
        if (trainerWithSameName != null) {
            throw new SameNameException();
        }
        return trainerRepo.save(trainer);
    }

    @Override
    public List<Trainer> getAll() {
        return trainerRepo.findAll();
    }
}
