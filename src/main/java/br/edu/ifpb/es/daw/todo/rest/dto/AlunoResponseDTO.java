package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
public record AlunoResponseDTO(
        UUID lookupId,
        String nome,
        String matricula,
        String email,
        LocalDate dataNascimento,
        TurmaResponseDTO turma,
        Set<Long> aulaIds
) {}
