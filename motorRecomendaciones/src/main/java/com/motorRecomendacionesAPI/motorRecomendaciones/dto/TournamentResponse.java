package com.motorRecomendacionesAPI.motorRecomendaciones.dto;

import java.time.Instant;
import java.util.UUID;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.TournamentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentResponse {

    private UUID id;
    private String name;
    private Instant startDate;
    private Instant endDate;
    private Instant registrationOpenAt;
    private Instant registrationCloseAt;
    private TournamentStatus status;
    private Instant createdAt;
}
