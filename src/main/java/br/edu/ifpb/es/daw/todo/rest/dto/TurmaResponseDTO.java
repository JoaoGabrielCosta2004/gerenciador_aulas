package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record TurmaResponseDTO(
        UUID lookupId,
        String nome,
        Integer ano
) {}
