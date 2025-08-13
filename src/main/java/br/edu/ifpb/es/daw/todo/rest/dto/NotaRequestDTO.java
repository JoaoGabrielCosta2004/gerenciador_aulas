package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

@Builder
public record NotaRequestDTO(
        String descricao,
        Double valor,
        Long alunoId,
        Long disciplinaId,
        Long materialId
) {}
