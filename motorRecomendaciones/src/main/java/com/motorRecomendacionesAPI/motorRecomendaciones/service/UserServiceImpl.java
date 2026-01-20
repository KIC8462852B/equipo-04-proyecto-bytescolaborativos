package com.motorRecomendacionesAPI.motorRecomendaciones.service;

import com.motorRecomendacionesAPI.motorRecomendaciones.exception.EmailAlreadyInUseException;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.UserNotFoundException;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.UsernameAlreadyInUseException;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.User;
import com.motorRecomendacionesAPI.motorRecomendaciones.repository.UserRepository;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User getUserById(UUID userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public void ensureUniqueCredentials(String email, String username) {
        if (repository.existsByEmail(email))
            throw new EmailAlreadyInUseException("El correo electrónico ya está en uso: " + email);
        if (repository.existsByUsername(username))
            throw new UsernameAlreadyInUseException("El nombre de usuario ya está en uso: " + username);
    }
}
