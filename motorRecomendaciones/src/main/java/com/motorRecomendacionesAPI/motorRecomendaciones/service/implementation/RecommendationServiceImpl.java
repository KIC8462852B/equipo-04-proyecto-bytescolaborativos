package com.motorRecomendacionesAPI.motorRecomendaciones.service.implementation;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.ProductResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RatingResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RecommendationResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.Product;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.Recommendation;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.User;
import com.motorRecomendacionesAPI.motorRecomendaciones.repository.RecommendationRepository;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.ProductService;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.RatingService;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.RecommendationService;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository repository;
    private final RatingService ratingService;
    private final ProductService productService;
    private final UserService userService;

    @Override
    public RecommendationResponse generateRecommendationsForUser(UUID userId) {
        final int MAX_RESULTS = 20;

        User user = userService.getUserById(userId);

        List<Product> userHighRated = ratingService.getProductsWithHighRatingByUser(user.getId());

        List<Product> candidates = determineCandidateProducts(userHighRated);

        List<Product> filtered = candidates.stream()
                .filter(p -> !userHighRated.contains(p))
                .distinct()
                .limit(MAX_RESULTS)
                .toList();

        var finalList = filtered.stream()
                .map(this::toProductResponse)
                .toList();

        Recommendation recommendation = repository.save(this.buildRecommendation(user, filtered));

        return new RecommendationResponse(recommendation.getGeneratedAt(), finalList);
    }

    private List<Product> determineCandidateProducts(List<Product> userHighRated) {
        if (userHighRated == null || userHighRated.isEmpty()) {
            return productService.getTopPopularProducts();
        }

        var tags = userHighRated.stream()
                .flatMap(product -> product.getTags().stream())
                .distinct()
                .toList();

        // if tags are empty, fallback to top popular products
        if (tags.isEmpty()) {
            return productService.getTopPopularProducts();
        }

        var byTags = productService.getProductsByTags(tags);
        // if tag search returned nothing, fallback to populars
        return (byTags == null || byTags.isEmpty()) ? productService.getTopPopularProducts() : byTags;
    }

    private ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getTags(),
                product.getPopularityScore()
        );
    }

    private Recommendation buildRecommendation(User user, List<Product> products) {
        return Recommendation.builder()
                .user(user)
                .products(products)
                .algorithmVersion("v1.0")
                .build();
    }
}
