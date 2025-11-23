package com.motorRecomendacionesAPI.motorRecomendaciones.service.implementation;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RatingRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RatingResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.DuplicateRatingException;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.RatingNotFoundException;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.Product;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.Rating;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.User;
import com.motorRecomendacionesAPI.motorRecomendaciones.repository.RatingRepository;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.ProductService;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.RatingService;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;
    private final ProductService productService;
    private final UserService userService;

    @Override
    @Transactional
    public RatingResponse createRating(RatingRequest request, String username) {
        User user = userService.getUserByEmail(username);
        Product product = productService.getProductById(request.productId());

        this.ensureUserHasNotRatedProduct(user, product);

        Rating rating = this.buildRating(user, product, request.score());
        Rating savedRating = repository.save(rating);

        Double averageScore = this.averageRating(product.getId());

        return new RatingResponse(
                "Rating created successfully",
                savedRating.getCreatedAt(),
                averageScore
        );
    }

    @Override
    @Transactional(readOnly = true)
    public RatingResponse averageScoreByProduct(UUID productId) {
        Double averaged = this.averageRating(productId);

        if (averaged == null) throw new RatingNotFoundException("No ratings found for product with ID " + productId);

        return new RatingResponse(
                "Average score retrieved successfully",
                Instant.now(),
                averaged
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsWithHighRatingByUser(UUID userId) {
        var ratings = repository.findHighRatingsByUserId(userId);
        return ratings.stream()
                .map(Rating::getProduct)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Double averageRating(UUID productId) {
        return repository.getAverageScoreByProductId(productId);
    }

    private Rating buildRating(User user, Product product, int score) {
        return Rating.builder()
                .user(user)
                .product(product)
                .score(score)
                .build();
    }

    private void ensureUserHasNotRatedProduct(User user, Product product) {
        boolean exists = repository.existsByUserIdAndProductId(user.getId(), product.getId());
        if (exists) {
            throw new DuplicateRatingException("User with ID " + user.getId() + " has already rated product with ID " + product.getId());
        }
    }
}
