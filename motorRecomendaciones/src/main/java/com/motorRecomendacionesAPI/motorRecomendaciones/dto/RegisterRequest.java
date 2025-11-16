package com.motorRecomendacionesAPI.motorRecomendaciones.dto;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RegisterRequest(
        @NotBlank(message = "the email must not be empty")
        @Size(max = 120, message = "then email must not exceed 120 characters")
        String email,
        @NotBlank(message = "the username must not be empty")
        @Size(max = 120, message = "then username must not exceed 120 characters")
        String username,
        @NotBlank(message = "the password must not be empty")
        @Size(min = 8, max = 20, message = "the password must be between 8 and 20 characters")
        String password,
        @NotEmpty(message = "the roles must not be empty")
        @Size(min = 1, max = 2, message = "the roles must contain between 1 and 2 entries")
        List<UserRole> roles
) {
}
