package fit.sudor.assessment.service.impl;

import fit.sudor.assessment.domain.Trainer;
import fit.sudor.assessment.exception.SameNameException;
import fit.sudor.assessment.repo.TrainerRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TrainerServiceImplTest {

    @MockBean
    private TrainerRepo trainerRepo;

    @Autowired
    private TrainerServiceImpl trainerService;

    @Test
    void save_throws_exception_if_name_already_used() {
        Mockito.when(trainerRepo.findFirstByNameIgnoreCase(Mockito.any())).thenReturn(new Trainer());

        Assertions.assertThrows(SameNameException.class, () -> trainerService.save(new Trainer()));
    }

    @Test
    void save_success() {
        Mockito.when(trainerRepo.findFirstByNameIgnoreCase(Mockito.any())).thenReturn(null);

        Trainer trainer = new Trainer();
        trainer.setId(1L);

        Mockito.when(trainerRepo.save(Mockito.any())).thenReturn(trainer);

        Trainer result = trainerService.save(new Trainer());

        Assertions.assertEquals(trainer.getId(), result.getId());
    }

    @Test
    void get_all_returns_result() {
        List<Trainer> trainers = List.of(new Trainer(), new Trainer());

        Mockito.when(trainerRepo.findAll()).thenReturn(trainers);

        List<Trainer> result = trainerService.getAll();

        Assertions.assertFalse(result.isEmpty());
    }
}