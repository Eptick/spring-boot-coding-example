package hr.redzicleon.library.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handles some exceptions and produces a correct response entity
 */
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Returns 404 if a entity can't be found in the result set
     * @param exception Generic exception
     * @param request request that produced the exceptions
     * @return Response entity
     */
    @ExceptionHandler({
            EntityNotFoundException.class,
            EmptyResultDataAccessException.class,
    })
    public ResponseEntity<Object> handleEntityNotFoundException(
            Exception exception,
            WebRequest request) {
        return ResponseEntity.notFound().build();
    }

    /**
     * If there are database constraint violations a bad request should be
     * @param exception
     * @return
     */
    @ExceptionHandler({
            ConstraintViolationException.class,
            IdentifierGenerationException.class
    })
    public ResponseEntity<Object> handleConstraintViolationException(
            Exception exception) {
        return ResponseEntity.badRequest().body(null);
    }

    /**
     * When a validaiton exception happens, format the response object to be
     * consistend and contain valuable information
     * @param constraintViolationException 
     * @return Object with key value pairs
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handle(
            javax.validation.ConstraintViolationException constraintViolationException) {
        Map<String, String> errors = new HashMap<>();
        constraintViolationException.getConstraintViolations().forEach((error) -> {
            String fieldName = (StreamSupport.stream(error.getPropertyPath().spliterator(), false)
                    .reduce((first, second) -> second).orElse(null)).toString();
            String errorMessage = error.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * When a validaiton exception happens, format the response object to be
     * consistend and contain valuable information
     * @param constraintViolationException 
     * @return Object with key value pairs
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
