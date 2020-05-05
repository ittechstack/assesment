package fit.sudor.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "There is not such a trainer")
public class TrainerNotFoundException extends RuntimeException {
}
