package com.motorRecomendacionesAPI.motorRecomendaciones.service;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.User;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID userId);

    User getUserByEmail(String email);

    void ensureUniqueCredentials(String email, String username);
}
