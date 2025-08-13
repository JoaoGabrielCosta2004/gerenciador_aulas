package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

@Builder
public record AulaRequestDTO(
        String data,          // formato dd/MM/yyyy
        String conteudo,
        Integer quantidadeFalta,
        Long professorId
) {}
