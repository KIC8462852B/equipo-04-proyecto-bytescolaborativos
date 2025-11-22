package com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RatingRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RatingResponse;

import java.util.UUID;

public interface RatingService {

    RatingResponse createRating(RatingRequest request, String username);

    RatingResponse averageScoreByProduct(UUID productId);
}
