package br.edu.ifpb.es.daw.todo.mapper;


import br.edu.ifpb.es.daw.todo.model.Disciplina;
import br.edu.ifpb.es.daw.todo.rest.dto.DisciplinaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.DisciplinaResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaMapper {

    public Disciplina from(DisciplinaRequestDTO dto) {
        return Disciplina.builder()
                .nome(dto.nome())
                .build();
    }

    public DisciplinaResponseDTO from(Disciplina entity) {
        return DisciplinaResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
    }
}
