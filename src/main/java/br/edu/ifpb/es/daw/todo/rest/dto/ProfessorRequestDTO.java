package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

@Builder
public record ProfessorRequestDTO(
        String nome,
        String email,
        String senha,
        Long disciplinaId
) {}
