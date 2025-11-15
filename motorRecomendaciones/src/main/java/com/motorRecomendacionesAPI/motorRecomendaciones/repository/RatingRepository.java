package com.motorRecomendacionesAPI.motorRecomendaciones.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, UUID> {
}
