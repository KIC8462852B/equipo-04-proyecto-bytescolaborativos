package com.motorRecomendacionesAPI.motorRecomendaciones.exception;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmailAlreadyInUseException extends CredentialAlreadyInUseException {
    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
