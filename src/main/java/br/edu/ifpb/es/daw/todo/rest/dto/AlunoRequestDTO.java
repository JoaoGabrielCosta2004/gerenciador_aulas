package br.edu.ifpb.es.daw.todo.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public record AlunoRequestDTO(
        @NotBlank String nome,
        @NotBlank String matricula,
        @Email String email,
        @NotNull LocalDate dataNascimento,
        @NotNull Long turmaId,       // ID interno da Turma
        Set<Long> aulaIds            // IDs das Aulas
) {}
