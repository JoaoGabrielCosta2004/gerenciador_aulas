package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

@Builder
public record MaterialResponseDTO(
        Long id,
        String tipo,
        String titulo,
        String link,
        Long aulaId
) {}
