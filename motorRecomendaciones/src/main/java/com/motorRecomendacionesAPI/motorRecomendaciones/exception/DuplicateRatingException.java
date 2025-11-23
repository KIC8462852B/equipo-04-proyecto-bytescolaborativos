package com.motorRecomendacionesAPI.motorRecomendaciones.exception;

public class DuplicateRatingException extends RuntimeException {
    public DuplicateRatingException(String message) {
        super(message);
    }
}
