package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

@Builder
public record NotaResponseDTO(
        Long id,
        String descricao,
        Double valor,
        Long alunoId,
        Long disciplinaId,
        Long materialId
) {}
