package com.motorRecomendacionesAPI.motorRecomendaciones.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.Rating;
import org.springframework.data.jpa.repository.Query;

public interface RatingRepository extends JpaRepository<Rating, UUID> {

    boolean existsByUserIdAndProductId(UUID userId, UUID productId);

    @Query("SELECT COALESCE(AVG(r.score), 0.0) FROM Rating r WHERE r.product.id = :productId")
    Double getAverageScoreByProductId(UUID productId);

    boolean existsByProductId(UUID productId);
}
