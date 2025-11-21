package com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.User;

public interface UserService {

    User getUserByEmail(String email);

    void ensureUniqueCredentials(String email, String username);
}
