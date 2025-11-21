package com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.User;

public interface UserService {

    User getUserByUsername(String username);

    void ensureUniqueCredentials(String email, String username);
}
