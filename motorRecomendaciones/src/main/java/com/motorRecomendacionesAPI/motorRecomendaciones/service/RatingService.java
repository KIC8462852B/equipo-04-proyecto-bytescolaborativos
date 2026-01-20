package com.motorRecomendacionesAPI.motorRecomendaciones.service;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RatingRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RatingResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.Product;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.Rating;

import java.util.List;
import java.util.UUID;

public interface RatingService {

    RatingResponse createRating(RatingRequest request, String username);

    RatingResponse averageScoreByProduct(UUID productId);

    List<Product> getProductsWithHighRatingByUser(UUID userId);

    Double averageRating(UUID productId);
}
