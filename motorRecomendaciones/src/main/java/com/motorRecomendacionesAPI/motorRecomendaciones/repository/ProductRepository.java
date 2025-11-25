package com.motorRecomendacionesAPI.motorRecomendaciones.repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.Product;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p JOIN p.tags t WHERE t IN :tags GROUP BY p.id ORDER BY p.popularityScore DESC")
    List<Product> findAllByTagsOrderByPopularityScoreDesc(Set<String> tags);

    @Query("SELECT p FROM Product p ORDER BY p.popularityScore ASC")
    List<Product> findAllOrderByPopularityScore();
}
