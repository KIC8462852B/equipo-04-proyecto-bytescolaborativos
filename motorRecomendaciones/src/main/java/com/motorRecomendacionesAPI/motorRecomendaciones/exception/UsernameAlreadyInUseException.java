package com.motorRecomendacionesAPI.motorRecomendaciones.exception;

public class UsernameAlreadyInUseException extends CredentialAlreadyInUseException {
    public UsernameAlreadyInUseException(String message) {
        super(message);
    }
}
