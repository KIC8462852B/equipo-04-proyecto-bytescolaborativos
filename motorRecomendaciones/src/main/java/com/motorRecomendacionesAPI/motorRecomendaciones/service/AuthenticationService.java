package com.motorRecomendacionesAPI.motorRecomendaciones.service;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.AuthenticationRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.AuthenticationResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse register(RegisterRequest request);
}
