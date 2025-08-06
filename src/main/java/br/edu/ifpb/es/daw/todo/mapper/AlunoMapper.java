package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Aluno;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno from(AlunoSalvarRequestDTO dto) {
        return Aluno.builder()
                .nome(dto.getNome())
                .matricula(dto.getMatricula())
                .email(dto.getEmail())
                .build();
    }

    public AlunoResponseDTO from(Aluno aluno) {
        AlunoResponseDTO dto = new AlunoResponseDTO();
        dto.setId(aluno.getId());
        dto.setLookupId(aluno.getLookupId());
        dto.setNome(aluno.getNome());
        dto.setMatricula(aluno.getMatricula());
        dto.setEmail(aluno.getEmail());
        return dto;
    }
}