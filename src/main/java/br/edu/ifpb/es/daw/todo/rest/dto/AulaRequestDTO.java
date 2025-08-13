package br.edu.ifpb.es.daw.todo.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public record AulaRequestDTO(
        @NotBlank String conteudo,
        @NotNull LocalDate data,
        @NotNull Long professorId,
        Set<Long> alunoIds // IDs dos alunos
) {}
