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

import com.motorRecomendacionesAPI.motorRecomendaciones.dto.ErrorResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.tournament.CreateTournamentRequest;
import com.motorRecomendacionesAPI.motorRecomendaciones.dto.tournament.TournamentResponse;
import com.motorRecomendacionesAPI.motorRecomendaciones.service.interfaces.TournamentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService service;

    @Operation(summary = "Create tournament", description = "Create a new tournament. Requires ADMIN role.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tournament created",
                    content = @Content(schema = @Schema(implementation = TournamentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TournamentResponse> createTournament(
            @Valid @RequestBody CreateTournamentRequest request
    ) {
        TournamentResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "List tournaments", description = "Retrieve paginated tournaments optionally filtered by status (UPCOMING, OPEN, CLOSED).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tournaments retrieved",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TournamentResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
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

    @Operation(summary = "Get tournament by id", description = "Retrieve a tournament by its identifier.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tournament found",
                    content = @Content(schema = @Schema(implementation = TournamentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Tournament not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TournamentResponse> getTournamentById(
            @PathVariable UUID id
    ) {
        TournamentResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete tournament", description = "Delete a tournament by id. Requires ADMIN role.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tournament deleted"),
            @ApiResponse(responseCode = "404", description = "Tournament not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTournament(
            @PathVariable UUID id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
