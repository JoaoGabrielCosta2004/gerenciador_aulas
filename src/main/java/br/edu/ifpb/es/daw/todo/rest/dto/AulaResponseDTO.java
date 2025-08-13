package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record AulaResponseDTO(
        Long id,
        String data,
        String conteudo,
        Integer quantidadeFalta,
        Long professorId,
        Set<Long> alunoIds
) {}
