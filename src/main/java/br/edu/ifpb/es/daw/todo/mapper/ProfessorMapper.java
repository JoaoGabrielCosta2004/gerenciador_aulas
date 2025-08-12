package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Disciplina;
import br.edu.ifpb.es.daw.todo.model.Professor;
import br.edu.ifpb.es.daw.todo.repository.DisciplinaRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.DisciplinaResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.ProfessorRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.ProfessorResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor from(ProfessorRequestDTO dto, Disciplina disciplina) {
        return Professor.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(dto.senha())
                .disciplina(disciplina)
                .build();
    }

    public ProfessorResponseDTO from(Professor professor) {
        Disciplina disciplina = professor.getDisciplina();

        DisciplinaResponseDTO disciplinaDTO = new DisciplinaResponseDTO(
                disciplina.getId(),
                disciplina.getNome()
        );

        return ProfessorResponseDTO.builder()
                .lookupId(professor.getLookupId())
                .nome(professor.getNome())
                .email(professor.getEmail())
                .disciplina(disciplinaDTO)
                .build();
    }

    public void updateProfessorFromDTO(Professor professor, ProfessorRequestDTO dto, Disciplina disciplina) {
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setSenha(dto.senha());
        professor.setDisciplina(disciplina);
    }
}
