package com.motorRecomendacionesAPI.motorRecomendaciones.controller;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.AuthenticationRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.AuthenticationResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.ErrorResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RegisterRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.AuthenticationService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticate users", description = "Authenticate a user with email and password and return a token.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful authentication"),
            @ApiResponse(responseCode = "401", description = "Wrong credentials", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Register users", description = "Registers a new user and returns an authentication token.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful registration"),
            @ApiResponse(responseCode = "409", description = "The email address or username is already in use.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }
}
