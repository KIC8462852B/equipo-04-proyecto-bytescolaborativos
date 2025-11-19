package com.motorRecomendacionesAPI.motorRecomendaciones.dto.tournament;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.motorRecomendacionesAPI.motorRecomendaciones.model.Tournament;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TournamentMapper {

    Tournament toEntity(CreateTournamentRequest request);

    TournamentResponse toResponse(Tournament tournament);
}
