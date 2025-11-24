package com.motorRecomendacionesAPI.motorRecomendaciones.advice;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.ErrorResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                String.valueOf(HttpStatus.NOT_FOUND.value()),
                Instant.now().toString(),
                request.getDescription(false).substring(4),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        ErrorResponse errorResponse = new ErrorResponse(
                message,
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                Instant.now().toString(),
                request.getDescription(false).substring(4),
                null
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
