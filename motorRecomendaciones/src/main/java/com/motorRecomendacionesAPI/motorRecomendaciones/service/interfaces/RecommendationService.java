package com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RecommendationResponse;

import java.util.UUID;

public interface RecommendationService {

    RecommendationResponse generateRecommendationsForUser(UUID userId);
}
