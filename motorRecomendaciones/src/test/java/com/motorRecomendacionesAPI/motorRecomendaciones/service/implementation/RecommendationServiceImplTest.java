package com.motorRecomendacionesAPI.motorRecomendaciones.service.implementation;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.Product;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.Recommendation;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.User;
import com.motorRecomendacionesAPI.motorRecomendaciones.repository.RecommendationRepository;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.ProductService;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.RatingService;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceImplTest {

    @Mock
    RecommendationRepository repository;

    @Mock
    RatingService ratingService;

    @Mock
    ProductService productService;

    @Mock
    UserService userService;

    @Captor
    ArgumentCaptor<Recommendation> recommendationCaptor;

    @InjectMocks
    RecommendationServiceImpl recommendationService;

    @Test
    @DisplayName("generateRecommendationsForUser -> uses tag-based candidates and filters out user's rated items")
    void whenUserHasHighRated_useTagsAndFilterRated() {
        UUID userUuid = UUID.randomUUID();
        User user = mock(User.class);
        when(user.getId()).thenReturn(userUuid);
        when(userService.getUserById(userUuid)).thenReturn(user);

        Product rated = mock(Product.class);
        UUID ratedId = UUID.randomUUID();
        when(rated.getId()).thenReturn(ratedId);
        when(rated.getTags()).thenReturn(Set.of("tagA"));

        Product candidate1 = mock(Product.class);
        when(candidate1.getId()).thenReturn(ratedId);

        Product candidate2 = mock(Product.class); // different product should remain
        when(candidate2.getId()).thenReturn(UUID.randomUUID());
        when(candidate2.getTags()).thenReturn(Set.of("tagA"));

        when(ratingService.getProductsWithHighRatingByUser(userUuid)).thenReturn(List.of(rated));
        when(productService.getProductsByTags(List.of("tagA"))).thenReturn(List.of(candidate1, candidate2));
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        var response = recommendationService.generateRecommendationsForUser(userUuid);

        verify(repository, times(1)).save(recommendationCaptor.capture());
        Recommendation saved = recommendationCaptor.getValue();
        assertNotNull(saved);
        List<Product> savedProducts = saved.getProducts();
        assertTrue(savedProducts.stream().noneMatch(p -> p.getId().equals(ratedId)));
        assertTrue(savedProducts.stream().anyMatch(p -> p.getId().equals(candidate2.getId())));
        assertNotNull(response);
    }

    @Test
    @DisplayName("generateRecommendationsForUser -> no high-rated products falls back to top popular products")
    void whenNoHighRated_useTopPopularFallback() {
        // Arrange
        UUID userUuid = UUID.randomUUID();
        User user = mock(User.class);
        when(user.getId()).thenReturn(userUuid);
        when(userService.getUserById(userUuid)).thenReturn(user);

        when(ratingService.getProductsWithHighRatingByUser(userUuid)).thenReturn(List.of());

        Product popular1 = mock(Product.class);
        when(popular1.getId()).thenReturn(UUID.randomUUID());
        Product popular2 = mock(Product.class);
        when(popular2.getId()).thenReturn(UUID.randomUUID());

        when(productService.getTopPopularProducts()).thenReturn(List.of(popular1, popular2));
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        var response = recommendationService.generateRecommendationsForUser(userUuid);

        // Assert
        verify(repository, times(1)).save(recommendationCaptor.capture());
        Recommendation saved = recommendationCaptor.getValue();
        List<Product> savedProducts = saved.getProducts();
        assertEquals(2, savedProducts.size());
        assertTrue(savedProducts.stream().anyMatch(p -> p.getId().equals(popular1.getId())));
        assertTrue(savedProducts.stream().anyMatch(p -> p.getId().equals(popular2.getId())));
        assertNotNull(response);
    }

    @Test
    @DisplayName("generateRecommendationsForUser -> propagates exception from userService")
    void whenUserServiceThrows_exceptionIsPropagated() {
        // Arrange
        UUID userUuid = UUID.randomUUID();
        when(userService.getUserById(userUuid)).thenThrow(new IllegalArgumentException("user not found"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> recommendationService.generateRecommendationsForUser(userUuid));
        verifyNoInteractions(repository);
    }

    @Test
    @DisplayName("generateRecommendationsForUser -> limits results to MAX_RESULTS")
    void limitsResultsToMax() {
        UUID userUuid = UUID.randomUUID();
        User user = mock(User.class);
        when(user.getId()).thenReturn(userUuid);
        when(userService.getUserById(userUuid)).thenReturn(user);

        Product rated = mock(Product.class);
        // Usamos lenient() aquí también por si tu lógica usa equals() en vez de getId() para filtrar
        lenient().when(rated.getId()).thenReturn(UUID.randomUUID());
        lenient().when(rated.getTags()).thenReturn(Set.of("popularTag"));
        when(ratingService.getProductsWithHighRatingByUser(userUuid)).thenReturn(List.of(rated));

        List<Product> many = new ArrayList<>();
        many.add(rated);

        // Generamos los productos de relleno
        IntStream.range(0, 25).forEach(i -> {
            Product p = mock(Product.class);
            // CORRECCIÓN AQUÍ: Agregamos lenient()
            lenient().when(p.getId()).thenReturn(UUID.randomUUID());
            lenient().when(p.getTags()).thenReturn(Set.of("popularTag"));
            many.add(p);
        });

        when(productService.getProductsByTags(List.of("popularTag"))).thenReturn(many);
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        var response = recommendationService.generateRecommendationsForUser(userUuid);

        verify(repository, times(1)).save(recommendationCaptor.capture());
        Recommendation saved = recommendationCaptor.getValue();
        List<Product> savedProducts = saved.getProducts();

        // Tu aserción original
        assertTrue(savedProducts.size() <= 20);
        assertNotNull(response);
    }

    @Test
    @DisplayName("generateRecommendationsForUser -> high rated items but no matching tags -> fallback to popular")
    void whenHighRatedButNoMatches_useTopPopularFallback() {
        UUID userUuid = UUID.randomUUID();
        User user = mock(User.class);
        when(userService.getUserById(userUuid)).thenReturn(user);
        when(user.getId()).thenReturn(userUuid);

        Product rated = mock(Product.class);
        when(rated.getTags()).thenReturn(Set.of("etiqueta_rara"));
        when(ratingService.getProductsWithHighRatingByUser(userUuid)).thenReturn(List.of(rated));

        when(productService.getProductsByTags(List.of("etiqueta_rara"))).thenReturn(List.of());

        Product popular = mock(Product.class);

        when(popular.getId()).thenReturn(UUID.randomUUID());
        when(popular.getName()).thenReturn("Producto Popular");
        when(popular.getDescription()).thenReturn("Desc");
        when(popular.getCategory()).thenReturn("Cat");
        when(popular.getTags()).thenReturn(Set.of("tag"));

        when(productService.getTopPopularProducts()).thenReturn(List.of(popular));

        when(repository.save(any())).thenAnswer(i -> i.getArgument(0));

        var response = recommendationService.generateRecommendationsForUser(userUuid);

        // Capture the saved recommendation from the repository before inspecting it
        verify(repository, times(1)).save(recommendationCaptor.capture());
        Recommendation saved = recommendationCaptor.getValue();

        assertNotNull(response);
        assertNotNull(saved);
        assertTrue(saved.getProducts().contains(popular));
    }
}

