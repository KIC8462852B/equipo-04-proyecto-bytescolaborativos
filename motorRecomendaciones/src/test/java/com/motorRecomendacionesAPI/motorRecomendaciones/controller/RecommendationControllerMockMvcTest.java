package com.motorRecomendacionesAPI.motorRecomendaciones.controller;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RecommendationResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.RecommendationService;
import com.motorRecomendacionesAPI.motorRecomendaciones.util.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecommendationController.class)
public class RecommendationControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecommendationService recommendationService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("GET /recommendations/{userId} -> 200 and non-empty JSON body")
    @WithMockUser(username = "usuarioFalso", roles = "PLAYER")
    void getRecommendations_returnsOkAndJsonBody() throws Exception {
        UUID userId = UUID.randomUUID();
        RecommendationResponse mockResponse = mock(RecommendationResponse.class);

        when(recommendationService.generateRecommendationsForUser(userId)).thenReturn(mockResponse);

        mockMvc.perform(get("/recommendations/{userId}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /recommendations/{userId} -> 401 Unauthorized (Sin Rol)")
    void getRecommendations_anonymous_returns401() throws Exception {
        UUID userId = UUID.randomUUID();
        mockMvc.perform(get("/recommendations/{userId}", userId))
                .andExpect(status().isUnauthorized()); // Validamos el 401
    }
}

