package com.motorRecomendacionesAPI.motorRecomendaciones.service.implementation;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.AuthenticationRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.AuthenticationResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RegisterRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.EmailAlreadyInUseException;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.UsernameAlreadyInUseException;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.User;
import com.motorRecomendacionesAPI.motorRecomendaciones.repository.UserRepository;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.AuthenticationService;
import com.motorRecomendacionesAPI.motorRecomendaciones.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = this.authenticateCredentials(request.email(), request.password());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new AuthenticationResponse(token, userDetails.getUsername(), Instant.now());
    }

    @Override
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        this.ensureUniqueCredentials(request.email(), request.username());

        User newUser = this.createUser(request);
        User savedUser = this.repository.save(newUser);

        Authentication authentication = this.createAuthentication(savedUser);
        String token = jwtUtil.generateToken(authentication);

        return new AuthenticationResponse(token, savedUser.getUsername(), Instant.now());
    }

    @Transactional(readOnly = true)
    public void ensureUniqueCredentials(String email, String username) {
        if (repository.existsByEmail(email)) throw new EmailAlreadyInUseException("email already in use: " + email);
        if (repository.existsByUsername(username))
            throw new UsernameAlreadyInUseException("username already in use: " + username);
    }

    private Authentication createAuthentication(User user) {
        var authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toSet());
        return new UsernamePasswordAuthenticationToken(user.getUsername(), null, authorities);
    }

    private Authentication authenticateCredentials(String email, String password) {
        UserDetails user = userDetailsService.loadUserByUsername(email);
        boolean matches = passwordEncoder.matches(password, user.getPassword());

        if (!matches) throw new BadCredentialsException("invalid credentials for the user " + email);

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    private User createUser(RegisterRequest request) {
        String hashedPassword = passwordEncoder.encode(request.password());
        return User.builder()
                .email(request.email())
                .username(request.username())
                .password(hashedPassword)
                .roles(new HashSet<>(request.roles()))
                .build();
    }
}
