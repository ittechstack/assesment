package fit.sudor.assessment.service;

import fit.sudor.assessment.domain.Trainer;

import java.util.List;

public interface TrainerService {

    Trainer save(Trainer trainer);

    List<Trainer> getAll();
}
