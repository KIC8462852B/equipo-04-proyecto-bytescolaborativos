package com.motorRecomendacionesAPI.motorRecomendaciones.controller;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.ErrorResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RatingRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RatingResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService service;

    @Operation(summary = "Create rating", description = "Create a rating for a product by the authenticated player.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Rating created", content = @Content(schema = @Schema(implementation = RatingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "User or product not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflict - duplicate rating", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<RatingResponse> addRating(@RequestBody @Valid RatingRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        var response = service.createRating(request, username);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get average rating", description = "Retrieve the average rating for a given product by productId.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Average rating retrieved", content = @Content(schema = @Schema(implementation = RatingResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product or rating not found", content = @Content(schema = @Schema(implementation = com.motorRecomendacionesAPI.motorRecomendaciones.dto.ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping("/{productId}")
    public ResponseEntity<RatingResponse> getAverageRating(@PathVariable UUID productId) {
        var response = service.averageScoreByProduct(productId);
        return ResponseEntity.ok(response);
    }
}