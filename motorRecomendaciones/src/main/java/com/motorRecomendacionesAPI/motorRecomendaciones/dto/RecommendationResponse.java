package com.motorRecomendacionesAPI.motorRecomendaciones.dto;

import java.time.Instant;
import java.util.List;

public record RecommendationResponse(
        Instant timestamp,
        List<ProductResponse> recommendedProducts
) {
}
