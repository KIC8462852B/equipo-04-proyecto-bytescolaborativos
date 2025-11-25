package com.motorRecomendacionesAPI.motorRecomendaciones.controller;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.AuthenticationRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.AuthenticationResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RegisterRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.EmailAlreadyInUseException;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.UserRole;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motorRecomendacionesAPI.motorRecomendaciones.util.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthenticationControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthenticationService authenticationService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("POST /auth/login -> success: returns 200")
    void login_success() throws Exception {
        AuthenticationResponse realResponse = new AuthenticationResponse("token123", "user", Instant.now());

        when(authenticationService.authenticate(any())).thenReturn(realResponse);

        AuthenticationRequest request = new AuthenticationRequest("user@example.com", "123456789");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(authenticationService, times(1)).authenticate(any());
    }

    @Test
    @DisplayName("POST /auth/login -> Bad Credentials -> returns 401 Unauthorized")
    void login_badCredentials_returns401() throws Exception {
        when(authenticationService.authenticate(any()))
                .thenThrow(new BadCredentialsException("Credenciales inválidas"));

        AuthenticationRequest request = new AuthenticationRequest("user@example.com", "wrongpass");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Credenciales inválidas"));

        verify(authenticationService, times(1)).authenticate(any());
    }

    @Test
    @DisplayName("POST /auth/register -> success: returns 200")
    void register_success() throws Exception {
        AuthenticationResponse realResponse = new AuthenticationResponse("token123", "newUser", Instant.now());

        when(authenticationService.register(any())).thenReturn(realResponse);

        RegisterRequest request = new RegisterRequest("newuser@example.com", "123456789", "New User", List.of(UserRole.ADMIN));

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(authenticationService, times(1)).register(any());
    }

    @Test
    @DisplayName("POST /auth/register -> Email already in use -> returns 409 Conflict")
    void register_duplicateEmail_returns409() throws Exception {
        when(authenticationService.register(any()))
                .thenThrow(new EmailAlreadyInUseException("El email ya está en uso"));

        RegisterRequest request = new RegisterRequest("existing@example.com", "existing_user", "123456789", List.of(UserRole.PLAYER));

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("El email ya está en uso"));

        verify(authenticationService, times(1)).register(any());
    }
}