package com.motorRecomendacionesAPI.motorRecomendaciones.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.Tournament;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.TournamentStatus;

public interface TournamentRepository extends JpaRepository<Tournament, UUID> {
 
    // OBTENEMOS TORNEOS POR ESTADO (OPEN, UPCOMING, CLOSED)
    List<Tournament> findByStatus(TournamentStatus status);
}

