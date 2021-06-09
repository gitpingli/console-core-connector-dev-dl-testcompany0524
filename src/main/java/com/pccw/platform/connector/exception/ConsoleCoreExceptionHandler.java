package com.pccw.platform.connector.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
@Order
@Slf4j
public class ConsoleCoreExceptionHandler {

  @ExceptionHandler(value = {ConsoleCoreException.class})
  ResponseEntity<ConsoleCoreError> handleMethodArgumentNotValidException(
      ConsoleCoreException e, HttpServletRequest request) {
    log.error("Enter exception handler.", e);
    return new ResponseEntity<>(
        ConsoleCoreError.builder().code(e.getCode()).error(e.getMessage()).build(),
        HttpStatus.valueOf(e.getCode()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ConsoleCoreError> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              var fieldName = ((FieldError) error).getField();
              var errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return new ResponseEntity<>(
        new ConsoleCoreError(HttpStatus.BAD_REQUEST.value(), StringUtils.join(errors)),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ConsoleCoreError> handleConstraintViolationException(
      ConstraintViolationException constraintViolationException) {
    Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
    Map<String, String> errors = new HashMap<>();
    if (!violations.isEmpty()) {
      violations.forEach(
          violation -> {
            var fieldName = violation.getPropertyPath().toString();
            var errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
          });
    } else {
      errors.put("Validation error", "ConstraintViolationException occured.");
    }
    return new ResponseEntity<>(
        new ConsoleCoreError(HttpStatus.BAD_REQUEST.value(), StringUtils.join(errors)),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(WebClientResponseException.class)
  public ResponseEntity<ConsoleCoreError> handleWebClientResponseException(
      WebClientResponseException ex) {
    log.error("Enter exception handler.", ex);
    return new ResponseEntity<>(
        new ConsoleCoreError(ex.getRawStatusCode(), ex.getResponseBodyAsString()),
        ex.getStatusCode());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ConsoleCoreError> handleAllException(
      Exception e, HttpServletRequest request, HttpServletResponse response) {
    log.error("Enter global exception handler.", e);
    return new ResponseEntity<>(
        ConsoleCoreError.builder().code(500).error(e.getMessage()).build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
