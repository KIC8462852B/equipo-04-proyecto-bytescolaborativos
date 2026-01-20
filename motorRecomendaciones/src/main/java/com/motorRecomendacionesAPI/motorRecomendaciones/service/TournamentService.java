package com.motorRecomendacionesAPI.motorRecomendaciones.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.CreateTournamentRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.TournamentResponse;

public interface TournamentService {

    TournamentResponse create(CreateTournamentRequest request);

    Page<TournamentResponse> findAll(Pageable pageable, String statusFilter);

    TournamentResponse findById(UUID id);

    void delete(UUID id);
}
