package com.motorRecomendacionesAPI.motorRecomendaciones.exception;


public class EmailAlreadyInUseException extends CredentialAlreadyInUseException {
    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
