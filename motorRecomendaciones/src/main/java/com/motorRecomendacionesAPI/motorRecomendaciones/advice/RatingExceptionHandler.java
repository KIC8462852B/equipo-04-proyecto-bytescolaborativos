package com.motorRecomendacionesAPI.motorRecomendaciones.advice;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.ErrorResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.DuplicateRatingException;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.ProductNotFoundException;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.ResourceNotFoundException;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class RatingExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, ProductNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                status.getReasonPhrase(),
                Instant.now().toString(),
                request.getDescription(false).substring(4),
                null
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(DuplicateRatingException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateRatingException(DuplicateRatingException exception, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                status.getReasonPhrase(),
                Instant.now().toString(),
                request.getDescription(false).substring(4),
                null
        );
        return ResponseEntity.status(status).body(errorResponse);
    }
}
