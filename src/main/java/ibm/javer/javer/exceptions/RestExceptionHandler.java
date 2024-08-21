package ibm.javer.javer.exceptions;

import ibm.javer.javer.dtos.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<Map<String, List<ValidationErrorModel>>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ValidationErrorModel> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationErrorModel(fieldError.getField(), fieldError.getDefaultMessage()))
                .sorted(Comparator.comparing(ValidationErrorModel::getField))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO<>("Erro ao validar objeto", getErrorsMap(errors), null));
    }

    private Map<String, List<ValidationErrorModel>> getErrorsMap(List<ValidationErrorModel> errors) {
        Map<String, List<ValidationErrorModel>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ResponseDTO<UserExistException>> userExistErrorHandler(UserExistException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDTO<>(exception.getMessage(), null, null));
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<ResponseDTO<UserNotExistException>> userNotExistErrorHandler(UserNotExistException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO<>(exception.getMessage(), null, null));
    }

}
