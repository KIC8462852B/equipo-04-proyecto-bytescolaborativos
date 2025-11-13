package com.motorRecomendacionesAPI.motorRecomendaciones.repository;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio JPA para la entidad Product
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    /**
     * Busca productos activos
     * @param pageable Paginación
     * @return Página de productos activos
     */
    Page<Product> findByIsActiveTrue(Pageable pageable);

    /**
     * Busca productos por nombre (contiene)
     * @param name Nombre a buscar
     * @param pageable Paginación
     * @return Página de productos que coinciden
     */
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    /**
     * Busca productos activos por nombre
     * @param name Nombre a buscar
     * @param pageable Paginación
     * @return Página de productos activos que coinciden
     */
    Page<Product> findByIsActiveTrueAndNameContainingIgnoreCase(String name, Pageable pageable);

    /**
     * Busca productos activos por rango de precio
     * @param minPrice Precio mínimo
     * @param maxPrice Precio máximo
     * @param pageable Paginación
     * @return Página de productos en el rango de precio
     */
    Page<Product> findByIsActiveTrueAndPriceBetween(
        BigDecimal minPrice, 
        BigDecimal maxPrice, 
        Pageable pageable
    );

    /**
     * Obtiene los productos más valorados (con mejor rating promedio)
     * @param limit Número máximo de productos
     * @return Lista de productos mejor valorados
     */
    @Query("""
        SELECT p FROM Product p
        LEFT JOIN Rating r ON r.product.id = p.id
        WHERE p.isActive = true
        GROUP BY p.id
        ORDER BY AVG(r.score) DESC NULLS LAST, COUNT(r.id) DESC
        """)
    List<Product> findTopRatedProducts(Pageable pageable);

    /**
     * Obtiene productos sin valoraciones
     * @return Lista de productos sin ratings
     */
    @Query("""
        SELECT p FROM Product p
        WHERE p.isActive = true
        AND NOT EXISTS (SELECT 1 FROM Rating r WHERE r.product.id = p.id)
        ORDER BY p.createdAt DESC
        """)
    List<Product> findProductsWithoutRatings();
}

