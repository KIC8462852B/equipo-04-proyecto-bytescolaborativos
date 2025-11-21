package com.motorRecomendacionesAPI.motorRecomendaciones.exception;

public class RatingNotFoundException extends ResourceNotFoundException {
    public RatingNotFoundException(String message) {
        super(message);
    }
}
