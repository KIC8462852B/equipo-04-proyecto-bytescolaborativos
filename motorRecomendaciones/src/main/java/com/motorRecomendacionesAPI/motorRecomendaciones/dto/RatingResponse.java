package com.motorRecomendacionesAPI.motorRecomendaciones.dto;

import java.time.Instant;

public record RatingResponse(
        String message,
        Instant timestamp,
        Double averageScore
) {
}
