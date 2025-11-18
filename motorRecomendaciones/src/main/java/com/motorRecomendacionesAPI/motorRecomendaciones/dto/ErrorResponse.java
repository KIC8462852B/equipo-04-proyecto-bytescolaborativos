package com.motorRecomendacionesAPI.motorRecomendaciones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"message", "error", "timestamp", "path", "metadata"})
public record ErrorResponse(
        String message,
        String error,
        String timestamp,
        String path,
        Object metadata) {
}