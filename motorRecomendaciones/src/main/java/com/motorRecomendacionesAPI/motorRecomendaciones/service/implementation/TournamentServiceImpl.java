package com.motorRecomendacionesAPI.motorRecomendaciones.service.implementation;
import java.time.Instant;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.tournament.CreateTournamentRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.tournament.TournamentMapper;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.tournament.TournamentResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.exception.ResourceNotFoundException;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.Tournament;
import com.motorRecomendacionesAPI.motorRecomendaciones.model.TournamentStatus;
import com.motorRecomendacionesAPI.motorRecomendaciones.repository.TournamentRepository;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.TournamentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository repository;
    private final TournamentMapper mapper;

    @Override
    public TournamentResponse create(CreateTournamentRequest request) {

        validateDateCoherence(request);

        Tournament entity = mapper.toEntity(request);

        entity.setStatus(TournamentStatus.UPCOMING);

        Tournament saved = repository.save(entity);

        return mapper.toResponse(saved);
    }

    @Override
    public Page<TournamentResponse> findAll(Pageable pageable, String statusFilter) {

        if (statusFilter != null) {
            TournamentStatus status = TournamentStatus.valueOf(statusFilter.toUpperCase());
            return repository.findByStatus(status)
                    .stream()
                    .map(mapper::toResponse)
                    .collect(java.util.stream.Collectors.collectingAndThen(
                            java.util.stream.Collectors.toList(),
                            list -> new org.springframework.data.domain.PageImpl<>(list, pageable, list.size())
                    ));
        }

        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public TournamentResponse findById(UUID id) {
    Tournament entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado"));

    return mapper.toResponse(entity);
}
  
@Override
public void delete(UUID id) {
    if (!repository.existsById(id)) {
        throw new ResourceNotFoundException("Torneo no encontrado");
    }
    repository.deleteById(id);
}

    private void validateDateCoherence(CreateTournamentRequest request) {

        Instant now = Instant.now();

        if (request.getStartDate().isBefore(now)) {
            throw new IllegalArgumentException("startDate must be in the future");
        }

        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException("endDate must be after startDate");
        }

        if (request.getRegistrationOpenAt().isAfter(request.getRegistrationCloseAt())) {
            throw new IllegalArgumentException("registrationOpenAt must be before registrationCloseAt");
        }

        if (request.getRegistrationCloseAt().isAfter(request.getStartDate())) {
            throw new IllegalArgumentException("registrationCloseAt must be before startDate");
        }
    }
}
