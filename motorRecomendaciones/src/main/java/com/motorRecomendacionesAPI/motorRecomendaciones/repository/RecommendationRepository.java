package com.motorRecomendacionesAPI.motorRecomendaciones.repository;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.Recommendation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio JPA para la entidad Recommendation
 */
@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, UUID> {

    /**
     * Busca recomendaciones activas para un usuario ordenadas por score
     * @param userId ID del usuario
     * @param now Fecha actual para filtrar expiradas
     * @param pageable Paginación
     * @return Página de recomendaciones activas
     */
    @Query("""
        SELECT r FROM Recommendation r
        WHERE r.user.id = :userId
        AND (r.expiresAt IS NULL OR r.expiresAt > :now)
        ORDER BY r.score DESC NULLS LAST, r.createdAt DESC
        """)
    Page<Recommendation> findActiveRecommendationsByUserId(
        @Param("userId") UUID userId,
        @Param("now") OffsetDateTime now,
        Pageable pageable
    );

    /**
     * Busca todas las recomendaciones de un usuario
     * @param userId ID del usuario
     * @param pageable Paginación
     * @return Página de recomendaciones
     */
    Page<Recommendation> findByUserIdOrderByScoreDescCreatedAtDesc(UUID userId, Pageable pageable);

    /**
     * Busca una recomendación específica de un usuario para un producto
     * @param userId ID del usuario
     * @param productId ID del producto
     * @return Optional con la recomendación si existe
     */
    Optional<Recommendation> findByUserIdAndProductId(UUID userId, UUID productId);

    /**
     * Busca recomendaciones no mostradas al usuario
     * @param userId ID del usuario
     * @param now Fecha actual
     * @param pageable Paginación
     * @return Lista de recomendaciones no mostradas
     */
    @Query("""
        SELECT r FROM Recommendation r
        WHERE r.user.id = :userId
        AND r.isShown = false
        AND (r.expiresAt IS NULL OR r.expiresAt > :now)
        ORDER BY r.score DESC NULLS LAST
        """)
    List<Recommendation> findUnshownRecommendations(
        @Param("userId") UUID userId,
        @Param("now") OffsetDateTime now,
        Pageable pageable
    );

    /**
     * Marca una recomendación como mostrada
     * @param id ID de la recomendación
     */
    @Modifying
    @Query("UPDATE Recommendation r SET r.isShown = true WHERE r.id = :id")
    void markAsShown(@Param("id") UUID id);

    /**
     * Marca una recomendación como clickeada
     * @param id ID de la recomendación
     */
    @Modifying
    @Query("UPDATE Recommendation r SET r.isClicked = true WHERE r.id = :id")
    void markAsClicked(@Param("id") UUID id);

    /**
     * Calcula el CTR (Click-Through Rate) para un usuario
     * @param userId ID del usuario
     * @return CTR como decimal
     */
    @Query("""
        SELECT CAST(SUM(CASE WHEN r.isClicked = true THEN 1 ELSE 0 END) AS double) / 
               CAST(COUNT(r.id) AS double)
        FROM Recommendation r
        WHERE r.user.id = :userId AND r.isShown = true
        """)
    Double calculateCTRByUserId(@Param("userId") UUID userId);

    /**
     * Obtiene las recomendaciones más clickeadas
     * @param pageable Paginación
     * @return Lista de productos más clickeados en recomendaciones
     */
    @Query("""
        SELECT r.product, COUNT(r.id) as clicks
        FROM Recommendation r
        WHERE r.isClicked = true
        GROUP BY r.product.id
        ORDER BY clicks DESC
        """)
    List<Object[]> findMostClickedRecommendations(Pageable pageable);

    /**
     * Elimina recomendaciones expiradas
     * @param now Fecha actual
     * @return Número de registros eliminados
     */
    @Modifying
    @Query("DELETE FROM Recommendation r WHERE r.expiresAt IS NOT NULL AND r.expiresAt <= :now")
    int deleteExpiredRecommendations(@Param("now") OffsetDateTime now);

    /**
     * Verifica si existe una recomendación activa para un usuario y producto
     * @param userId ID del usuario
     * @param productId ID del producto
     * @param now Fecha actual
     * @return true si existe, false si no
     */
    @Query("""
        SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
        FROM Recommendation r
        WHERE r.user.id = :userId
        AND r.product.id = :productId
        AND (r.expiresAt IS NULL OR r.expiresAt > :now)
        """)
    boolean existsActiveRecommendation(
        @Param("userId") UUID userId,
        @Param("productId") UUID productId,
        @Param("now") OffsetDateTime now
    );
}

