package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

@Builder
public record DisciplinaResponseDTO(
        Long id,
        String nome
) {}