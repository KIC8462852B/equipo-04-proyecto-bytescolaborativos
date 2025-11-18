package com.motorRecomendacionesAPI.motorRecomendaciones.exception;

public class CredentialAlreadyInUseException extends RuntimeException {
    public CredentialAlreadyInUseException(String message) {
        super(message);
    }
}
