package com.motorRecomendacionesAPI.motorRecomendaciones.dto.tournament;

import java.time.Instant;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTournamentRequest {

    @NotBlank(message = "El nombre del torneo es obligatorio")
    private String name;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Future(message = "La fecha de inicio debe ser futura")
    private Instant startDate;

    @NotNull(message = "La fecha de fin es obligatoria")
    @Future(message = "La fecha de fin debe ser futura")
    private Instant endDate;

    @NotNull(message = "La fecha de apertura de registro es obligatoria")
    private Instant registrationOpenAt;

    @NotNull(message = "La fecha de cierre de registro es obligatoria")
    private Instant registrationCloseAt;
}
