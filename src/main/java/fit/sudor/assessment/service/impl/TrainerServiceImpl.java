package fit.sudor.assessment.service.impl;

import fit.sudor.assessment.domain.Trainer;
import fit.sudor.assessment.repo.TrainerRepo;
import fit.sudor.assessment.service.TrainerService;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {

    private TrainerRepo trainerRepo;

    public TrainerServiceImpl(TrainerRepo trainerRepo) {
        this.trainerRepo = trainerRepo;
    }

    @Override
    public Trainer save(Trainer trainer) {
        return trainerRepo.save(trainer);
    }
}
