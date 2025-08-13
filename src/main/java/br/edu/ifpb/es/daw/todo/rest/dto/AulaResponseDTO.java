package br.edu.ifpb.es.daw.todo.rest.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
public record AulaResponseDTO(
        Long id,
        String conteudo,
        LocalDate data,
        Integer quantidadeFalta,
        ProfessorResponseDTO professor,
        Set<AlunoResponseDTO> alunos
) {}
