package Hahn.ApplicationProcess.Aplication.Aufgabe.exceptionHandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequest.class)
    protected ResponseEntity<Object> handleBadRequest(BadRequest br, WebRequest request){
        CustomBadRequestResponse badRequestResponse = new CustomBadRequestResponse(LocalDateTime.now(), br.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(badRequestResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, 
    WebRequest request){
        List<ApiValidationError> validationErrors = new ArrayList<>();
        String errorMsg = "Bad request was made because invalid values were input.";
        Set<ConstraintViolation<?>> constraintErrors = ex.getConstraintViolations();
        for(ConstraintViolation<?> constaints: constraintErrors){
            ApiValidationError apiValidationError = new ApiValidationError(constaints.getRootBeanClass().getSimpleName(),
                constaints.getMessage());
            validationErrors.add(apiValidationError);
        }
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, errorMsg, request.getDescription(true), validationErrors));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, 
        HttpStatus status, WebRequest request) {
        List<ApiValidationError> validationErrors = new ArrayList<>();
        String errorMsg = "Bad request made because invalid values were input.";
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            ApiValidationError apiValidationError = new ApiValidationError(error.getObjectName(),
            error.getDefaultMessage());
            validationErrors.add(apiValidationError);
        });
     
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, errorMsg, request.getDescription(true), validationErrors));
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, 
    HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException  ex, 
    HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Invalid request made because one or more request parameters were missing.";
        return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
    EntityNotFoundException ex) {
        ApiErrorResponse apiError = new ApiErrorResponse(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
   }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}