package com.gastelob.apiuser.exception;

import com.gastelob.apiuser.common.StandardizedApiExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<StandardizedApiExceptionResponse> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        log.info(e.getMessage());
        String errorMessage = "El usuario con el correo electrónico especificado, ya se encuentra registrado";
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse(errorMessage);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<StandardizedApiExceptionResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.info(e.getMessage());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse(errors.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<StandardizedApiExceptionResponse> handleDataIntegrityViolation(HttpMessageNotReadableException e) {
        log.info(e.getMessage());
        String errorMessage = "El formato del JSON es incorrecto. Revise la estructura y los tipos de datos.";
        StandardizedApiExceptionResponse response = new StandardizedApiExceptionResponse(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
