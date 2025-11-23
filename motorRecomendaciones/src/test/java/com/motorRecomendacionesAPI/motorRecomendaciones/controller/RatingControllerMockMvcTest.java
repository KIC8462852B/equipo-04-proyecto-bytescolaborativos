package com.motorRecomendacionesAPI.motorRecomendaciones.controller;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RatingRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RatingResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.DuplicateRatingException;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.RatingNotFoundException;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.RatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motorRecomendacionesAPI.motorRecomendaciones.util.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RatingController.class)
public class RatingControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RatingService ratingService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("POST /ratings -> success: returns 201 and calls service")
    @WithMockUser(username = "usuarioFalso", roles = "PLAYER")
    void addRating_success() throws Exception {
        UUID productId = UUID.randomUUID();
        RatingResponse realResponse = new RatingResponse("Success", Instant.now(), 5.0);
        when(ratingService.createRating(any(), anyString())).thenReturn(realResponse);

        RatingRequest request = new RatingRequest(productId, 5);

        mockMvc.perform(post("/ratings")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(ratingService, times(1)).createRating(any(), anyString());
    }

    @Test
    @DisplayName("GET /ratings/{productId} -> success: returns 200 and calls service")
    @WithMockUser(username = "usuarioFalso", roles = "PLAYER")
    void getAverageRating_success() throws Exception {
        UUID productId = UUID.randomUUID();
        RatingResponse realResponse = new RatingResponse("Avg retrieved", Instant.now(), 4.5);
        when(ratingService.averageScoreByProduct(productId)).thenReturn(realResponse);

        mockMvc.perform(get("/ratings/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(ratingService, times(1)).averageScoreByProduct(productId);
    }

    @Test
    @DisplayName("POST /ratings -> duplicate: returns 409 Conflict")
    @WithMockUser(username = "usuarioFalso", roles = "PLAYER")
    void addRating_duplicate_returns409() throws Exception {
        UUID productId = UUID.randomUUID();

        // Simulamos que el servicio lanza la excepción de negocio
        when(ratingService.createRating(any(), anyString()))
                .thenThrow(new DuplicateRatingException("User has already rated product"));

        Map<String, Object> payload = Map.of(
                "productId", productId.toString(),
                "score", 4
        );

        mockMvc.perform(post("/ratings")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                // Validamos que el ControllerAdvice transforme la excepción en 409
                .andExpect(status().isConflict())
                // Opcional: Validar que el JSON de error tenga el mensaje correcto
                .andExpect(jsonPath("$.message").value("User has already rated product"));

        verify(ratingService, times(1)).createRating(any(), anyString());
    }

    @Test
    @DisplayName("GET /ratings/{productId} -> not found: returns 404 Not Found")
    @WithMockUser(username = "usuarioFalso", roles = "PLAYER")
    void getAverageRating_notFound_returns404() throws Exception {
        UUID productId = UUID.randomUUID();

        // Simulamos que el servicio no encuentra ratings
        when(ratingService.averageScoreByProduct(productId))
                .thenThrow(new RatingNotFoundException("No ratings found for product"));

        mockMvc.perform(get("/ratings/{productId}", productId))
                // Validamos que el ControllerAdvice transforme la excepción en 404
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No ratings found for product"));

        verify(ratingService, times(1)).averageScoreByProduct(productId);
    }
}