package com.motorRecomendacionesAPI.motorRecomendaciones.exception;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UsernameAlreadyInUseException extends CredentialAlreadyInUseException {
    public UsernameAlreadyInUseException(String message) {
        super(message);
    }
}
