package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.rest.dto.TurmaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.TurmaResponseDTO;
import br.edu.ifpb.es.daw.todo.model.Turma;
import br.edu.ifpb.es.daw.todo.rest.dto.TurmaRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {

    public Turma from(TurmaRequestDTO dto) {
        return Turma.builder()
                .nome(dto.nome())
                .ano(dto.ano())
                .build();
    }

    public TurmaResponseDTO from(Turma entity) {
        return TurmaResponseDTO.builder()
                .lookupId(entity.getLookupId())
                .nome(entity.getNome())
                .ano(entity.getAno())
                .build();
    }
}
