package com.motorRecomendacionesAPI.motorRecomendaciones.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(nullable = false, updatable = false)
    private UUID id;

    // RELACIONES
    // RECOMENDACIÓN A USUARIO  N:1
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // RECOMENDACIÓN A PRODUCTOS  N:M
    @ManyToMany
    @JoinTable(
            name = "recommendation_products",
            joinColumns = @JoinColumn(name = "recommendation_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    // ATRIBUTOS
    @Column(nullable = false)
    private Instant generatedAt;

    @Column(nullable = false, length = 50)
    private String algorithmVersion;

    @PrePersist
    public void prePersist() {
        if (generatedAt == null) {
            generatedAt = Instant.now();
        }
    }
}
