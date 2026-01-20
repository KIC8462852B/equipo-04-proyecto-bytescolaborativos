package com.motorRecomendacionesAPI.motorRecomendaciones.service;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.AuthenticationRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.AuthenticationResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RegisterRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.EmailAlreadyInUseException;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.UsernameAlreadyInUseException;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.User;
import com.motorRecomendacionesAPI.motorRecomendaciones.enums.UserRole;
import com.motorRecomendacionesAPI.motorRecomendaciones.repository.UserRepository;
import com.motorRecomendacionesAPI.motorRecomendaciones.util.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserDetailsService userDetailsService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtUtil jwtUtil;

    @InjectMocks
    AuthenticationServiceImpl authenticationService;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("authenticate -> success: returns token and username")
    void authenticate_success() {
        // Arrange
        String email = "user@example.com";
        String rawPassword = "secret";
        String encodedPassword = "encoded_secret";
        String expectedToken = "jwt-token";
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getPassword()).thenReturn(encodedPassword);
        when(userDetails.getUsername()).thenReturn("user123");
        when(userDetails.getAuthorities()).thenReturn(Set.of());

        AuthenticationRequest request = mock(AuthenticationRequest.class);
        when(request.email()).thenReturn(email);
        when(request.password()).thenReturn(rawPassword);

        when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);
        when(jwtUtil.generateToken(any(Authentication.class))).thenReturn(expectedToken);

        AuthenticationResponse response = authenticationService.authenticate(request);

        assertNotNull(response);
        assertEquals(expectedToken, response.token());
        assertEquals("user123", response.username());
        assertNotNull(response.timestamp());
        verify(userDetailsService, times(1)).loadUserByUsername(email);
        verify(passwordEncoder, times(1)).matches(rawPassword, encodedPassword);
        verify(jwtUtil, times(1)).generateToken(any(Authentication.class));
    }

    @Test
    @DisplayName("authenticate -> bad credentials: throws BadCredentialsException")
    void authenticate_badCredentials_throws() {
        String email = "user2@example.com";
        String rawPassword = "wrong";
        String encodedPassword = "encoded_pw";

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getPassword()).thenReturn(encodedPassword);

        AuthenticationRequest request = mock(AuthenticationRequest.class);
        when(request.email()).thenReturn(email);
        when(request.password()).thenReturn(rawPassword);

        when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        // Act / Assert
        assertThrows(BadCredentialsException.class, () -> authenticationService.authenticate(request));
        verify(userDetailsService, times(1)).loadUserByUsername(email);
        verify(passwordEncoder, times(1)).matches(rawPassword, encodedPassword);
        verify(jwtUtil, never()).generateToken(any());
    }

    @Test
    @DisplayName("register -> success: saves user and returns token")
    void register_success() {
        String email = "new@example.com";
        String username = "newuser";
        String rawPassword = "passwd";
        String hashed = "hashed";
        String expectedToken = "reg-token";

        RegisterRequest request = mock(RegisterRequest.class);
        when(request.email()).thenReturn(email);
        when(request.username()).thenReturn(username);
        when(request.password()).thenReturn(rawPassword);
        when(request.roles()).thenReturn(List.of(UserRole.ADMIN));

        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(userRepository.existsByUsername(username)).thenReturn(false);

        when(passwordEncoder.encode(rawPassword)).thenReturn(hashed);

        User savedUser = mock(User.class);
        when(savedUser.getUsername()).thenReturn(username);
        when(savedUser.getRoles()).thenReturn(Set.of(UserRole.ADMIN));

        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtUtil.generateToken(any(Authentication.class))).thenReturn(expectedToken);

        var response = authenticationService.register(request);

        assertNotNull(response);
        assertEquals(expectedToken, response.token());
        assertEquals(username, response.username());
        assertNotNull(response.timestamp());
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(rawPassword);
        verify(jwtUtil, times(1)).generateToken(any(Authentication.class));
    }

    @Test
    @DisplayName("register -> email already exists: throws EmailAlreadyInUseException")
    void register_emailExists_throws() {
        String email = "exists@example.com";
        String username = "any";

        when(userRepository.existsByEmail(email)).thenReturn(true);

        assertThrows(EmailAlreadyInUseException.class, () -> userService.ensureUniqueCredentials(email, username));
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    @DisplayName("register -> username already exists: throws UsernameAlreadyInUseException")
    void register_usernameExists_throws() {
        String email = "noexists@example.com";
        String username = "taken";

        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(userRepository.existsByUsername(username)).thenReturn(true);

        assertThrows(UsernameAlreadyInUseException.class, () -> userService.ensureUniqueCredentials(email, username));
        verify(userRepository, times(1)).existsByUsername(username);
    }
}

