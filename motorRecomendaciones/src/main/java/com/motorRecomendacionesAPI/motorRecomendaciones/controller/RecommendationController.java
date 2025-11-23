package com.motorRecomendacionesAPI.motorRecomendaciones.controller;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.ErrorResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RecommendationResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Operation(
            summary = "Get recommendations for a user",
            description = "Generate personalized product recommendations for the specified user ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recommendations successfully generated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecommendationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid userId supplied"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access to recommendations"),
            @ApiResponse(responseCode = "404", description = "User or recommendations not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{userId}")
    public ResponseEntity<RecommendationResponse> getRecommendations(@PathVariable UUID userId) {
        var recommendations = recommendationService.generateRecommendationsForUser(userId);
        return ResponseEntity.ok(recommendations);
    }
}
