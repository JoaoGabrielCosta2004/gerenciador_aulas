package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

@Builder
public record DisciplinaRequestDTO(
        String nome
) {}