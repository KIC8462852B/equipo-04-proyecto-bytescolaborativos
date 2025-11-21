package com.motorRecomendacionesAPI.motorRecomendaciones.controller;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/ratings")
@PreAuthorize("hasRole('PLAYER')")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService service;

    @PostMapping
    public ResponseEntity<RatingResponse> addRating(@RequestBody @Valid RatingRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        var response = service.createRating(request, username);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<RatingResponse> getAverageRating(@PathVariable UUID productId) {
        var response = service.averageScoreByProduct(productId);
        return ResponseEntity.ok(response);
    }
}