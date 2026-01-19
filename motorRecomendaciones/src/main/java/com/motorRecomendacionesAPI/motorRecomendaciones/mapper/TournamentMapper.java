package com.motorRecomendacionesAPI.motorRecomendaciones.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.CreateTournamentRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.TournamentResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.Tournament;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TournamentMapper {

    Tournament toEntity(CreateTournamentRequest request);

    TournamentResponse toResponse(Tournament tournament);
}
