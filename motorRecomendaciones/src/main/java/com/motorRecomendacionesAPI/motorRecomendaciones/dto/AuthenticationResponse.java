package com.motorRecomendacionesAPI.motorRecomendaciones.dto;

import java.time.Instant;

public record AuthenticationResponse(
        String token,
        String username,
        Instant timestamp
) {
}