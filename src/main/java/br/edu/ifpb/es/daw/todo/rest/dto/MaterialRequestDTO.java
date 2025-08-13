package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

@Builder
public record MaterialRequestDTO(
        String tipo,
        String titulo,
        String link,
        Long aulaId
) {}
