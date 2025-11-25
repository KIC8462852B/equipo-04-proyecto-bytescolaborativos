package com.motorRecomendacionesAPI.motorRecomendaciones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        String category,
        Set<String> tags,
        Long popularityScore
) {
}

