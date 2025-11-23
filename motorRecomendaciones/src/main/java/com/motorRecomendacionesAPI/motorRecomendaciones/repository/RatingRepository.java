package com.motorRecomendacionesAPI.motorRecomendaciones.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.Rating;
import org.springframework.data.jpa.repository.Query;

public interface RatingRepository extends JpaRepository<Rating, UUID> {

    boolean existsByUserIdAndProductId(UUID userId, UUID productId);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.product.id = :productId")
    Double getAverageScoreByProductId(UUID productId);

    boolean existsByProductId(UUID productId);

    @Query("SELECT r FROM Rating r WHERE r.user.id = :userId AND r.score >= 4")
    List<Rating> findHighRatingsByUserId(UUID userId);

}
