package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ProfessorResponseDTO(
        UUID lookupId,
        String nome,
        String email,
        DisciplinaResponseDTO disciplina
) {}
