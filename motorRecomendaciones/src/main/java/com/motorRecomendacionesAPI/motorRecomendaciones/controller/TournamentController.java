package com.motorRecomendacionesAPI.motorRecomendaciones.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.tournament.CreateTournamentRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.tournament.TournamentResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.TournamentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService service;

   // CREAR TOURNAMENT (ADMIN ONLY)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TournamentResponse> createTournament(
            @Valid @RequestBody CreateTournamentRequest request
    ) {
        TournamentResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

   // OBTENER LISTA DE TOURNAMENTS (PUBLIC)
    @GetMapping
    public ResponseEntity<Page<TournamentResponse>> listTournaments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TournamentResponse> response = service.findAll(pageable, status);
        return ResponseEntity.ok(response);
    }

 // OBTENER TOURNAMENT POR ID (PUBLIC)
    @GetMapping("/{id}")
    public ResponseEntity<TournamentResponse> getTournamentById(
            @PathVariable UUID id
    ) {
        TournamentResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

   // ELIMINAR TOURNAMENT POR ID (ADMIN ONLY)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTournament(
            @PathVariable UUID id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
