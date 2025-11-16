package com.motorRecomendacionesAPI.motorRecomendaciones.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(
        @NotEmpty(message = "El username no debe estar vacío")
        @Size(max = 120, message = "El username no debe exceder 120 caracteres")
        String username,
        @NotEmpty(message = "La contraseña no debe estar vacía")
        @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
        String password
) {
}