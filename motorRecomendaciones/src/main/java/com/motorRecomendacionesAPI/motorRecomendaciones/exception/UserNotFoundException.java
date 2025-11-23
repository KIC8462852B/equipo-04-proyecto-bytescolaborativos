package com.motorRecomendacionesAPI.motorRecomendaciones.exception;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
