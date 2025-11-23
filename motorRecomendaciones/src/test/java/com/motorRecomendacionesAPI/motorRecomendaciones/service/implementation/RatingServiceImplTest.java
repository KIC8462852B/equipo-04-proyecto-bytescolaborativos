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
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingServiceImplTest {

    @Mock
    RatingRepository repository;

    @Mock
    ProductService productService;

    @Mock
    UserService userService;

    @InjectMocks
    RatingServiceImpl ratingService;

    @Test
    @DisplayName("createRating -> successful: saves rating and returns average")
    void createRating_success() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        String username = "user@example.com";
        int score = 5;

        RatingRequest request = mock(RatingRequest.class);
        when(request.productId()).thenReturn(productId);
        when(request.score()).thenReturn(score);

        User user = mock(User.class);
        when(userService.getUserByEmail(username)).thenReturn(user);
        when(user.getId()).thenReturn(userId);

        Product product = mock(Product.class);
        when(productService.getProductById(productId)).thenReturn(product);
        when(product.getId()).thenReturn(productId);

        when(repository.existsByUserIdAndProductId(userId, productId)).thenReturn(false);

        Rating savedRating = mock(Rating.class);
        Instant now = Instant.now();
        when(savedRating.getComputedAt()).thenReturn(now);
        when(repository.save(any(Rating.class))).thenReturn(savedRating);

        double avg = 4.2;
        when(repository.getAverageScoreByProductId(productId)).thenReturn(avg);

        // Act
        RatingResponse response = ratingService.createRating(request, username);

        // Assert
        assertNotNull(response);
        assertEquals("Rating created successfully", response.message());
        assertEquals(now, response.timestamp());
        assertEquals(avg, response.averageScore());
        // verify save called with a Rating instance
        verify(repository, times(1)).save(any(Rating.class));
    }

    @Test
    @DisplayName("createRating -> duplicate rating: throws DuplicateRatingException")
    void createRating_duplicate_throws() {
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        String username = "user2@example.com";

        RatingRequest request = mock(RatingRequest.class);
        when(request.productId()).thenReturn(productId);

        User user = mock(User.class);
        when(userService.getUserByEmail(username)).thenReturn(user);
        when(user.getId()).thenReturn(userId);

        Product product = mock(Product.class);
        lenient().when(productService.getProductById(productId)).thenReturn(product);
        lenient().when(product.getId()).thenReturn(productId);

        when(repository.existsByUserIdAndProductId(userId, productId)).thenReturn(true);

        assertThrows(DuplicateRatingException.class, () -> ratingService.createRating(request, username));

        verify(repository, never()).save(any(Rating.class));
    }

    @Nested
    @DisplayName("averageScoreByProduct scenarios")
    class AverageScoreTests {

        @Test
        @DisplayName("averageScoreByProduct -> success: returns averaged score")
        void averageScore_success() {
            // Arrange
            UUID productId = UUID.randomUUID();
            double avg = 3.7;
            when(repository.getAverageScoreByProductId(productId)).thenReturn(avg);

            // Act
            RatingResponse response = ratingService.averageScoreByProduct(productId);

            // Assert
            assertNotNull(response);
            assertEquals("Average score retrieved successfully", response.message());
            assertEquals(avg, response.averageScore());
            assertNotNull(response.timestamp());
        }

        @Test
        @DisplayName("averageScoreByProduct -> not found: throws RatingNotFoundException")
        void averageScore_notFound_throws() {
            // Arrange
            UUID productId = UUID.randomUUID();
            when(repository.getAverageScoreByProductId(productId)).thenReturn(null);

            // Act / Assert
            assertThrows(RatingNotFoundException.class, () -> ratingService.averageScoreByProduct(productId));
        }
    }
}

