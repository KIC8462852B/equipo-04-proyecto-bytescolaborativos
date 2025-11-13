package com.motorRecomendacionesAPI.motorRecomendaciones.repository;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.Rating;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.Product;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio JPA para la entidad Rating
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {

    /**
     * Busca un rating específico de un usuario para un producto
     * @param userId ID del usuario
     * @param productId ID del producto
     * @return Optional con el rating si existe
     */
    Optional<Rating> findByUserIdAndProductId(UUID userId, UUID productId);

    /**
     * Busca todos los ratings de un usuario
     * @param userId ID del usuario
     * @param pageable Paginación
     * @return Página de ratings del usuario
     */
    Page<Rating> findByUserIdOrderByCreatedAtDesc(UUID userId, Pageable pageable);

    /**
     * Busca todos los ratings de un producto
     * @param productId ID del producto
     * @param pageable Paginación
     * @return Página de ratings del producto
     */
    Page<Rating> findByProductIdOrderByCreatedAtDesc(UUID productId, Pageable pageable);

    /**
     * Obtiene el rating promedio de un producto
     * @param productId ID del producto
     * @return Rating promedio
     */
    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.product.id = :productId")
    Double getAverageScoreByProductId(@Param("productId") UUID productId);

    /**
     * Cuenta el número de ratings de un producto
     * @param productId ID del producto
     * @return Número de ratings
     */
    Long countByProductId(UUID productId);

    /**
     * Verifica si un usuario ya ha valorado un producto
     * @param userId ID del usuario
     * @param productId ID del producto
     * @return true si existe, false si no
     */
    boolean existsByUserIdAndProductId(UUID userId, UUID productId);

    /**
     * Busca ratings por rango de score
     * @param minScore Score mínimo
     * @param maxScore Score máximo
     * @param pageable Paginación
     * @return Página de ratings en el rango
     */
    Page<Rating> findByScoreBetweenOrderByCreatedAtDesc(
        Short minScore, 
        Short maxScore, 
        Pageable pageable
    );

    /**
     * Obtiene los productos con peor valoración
     * @param maxScore Score máximo a considerar
     * @param pageable Paginación
     * @return Lista de productos mal valorados
     */
    @Query("""
        SELECT r.product FROM Rating r
        WHERE r.score <= :maxScore
        GROUP BY r.product.id
        ORDER BY AVG(r.score) ASC, COUNT(r.id) DESC
        """)
    List<Product> findProductsWithLowRatings(
        @Param("maxScore") Short maxScore, 
        Pageable pageable
    );

    /**
     * Obtiene las estadísticas de rating de un producto
     * @param productId ID del producto
     * @return Array con [avgScore, count]
     */
    @Query("""
        SELECT AVG(r.score), COUNT(r.id)
        FROM Rating r
        WHERE r.product.id = :productId
        """)
    Object[] getProductRatingStats(@Param("productId") UUID productId);
}

