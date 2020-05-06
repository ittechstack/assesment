package fit.sudor.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "The name you specified is being used by another record")
public class SameNameException extends RuntimeException {
}
