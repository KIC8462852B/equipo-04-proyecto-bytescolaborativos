package com.motorRecomendacionesAPI.motorRecomendaciones.advice;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.ErrorResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.CredentialAlreadyInUseException;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.EmailAlreadyInUseException;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.UsernameAlreadyInUseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler({EmailAlreadyInUseException.class, UsernameAlreadyInUseException.class})
    public ResponseEntity<ErrorResponse> handleCredentialsAlreadyInUseException(CredentialAlreadyInUseException exception, WebRequest request) {
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

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(AuthenticationException exception, WebRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                status.getReasonPhrase(),
                Instant.now().toString(),
                request.getDescription(false).substring(4),
                null
        );
        return ResponseEntity.status(status).body(errorResponse);
    }}
