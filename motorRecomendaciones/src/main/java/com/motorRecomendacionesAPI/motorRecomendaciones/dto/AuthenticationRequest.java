package com.motorRecomendacionesAPI.motorRecomendaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(
        @NotBlank(message = "the email must not be empty")
        @Size(max = 120, message = "then email must not exceed 120 characters")
        String email,
        @NotBlank(message = "the password must not be empty")
        @Size(min = 8, max = 20, message = "the password must be between 8 and 20 characters")
        String password
) {
}