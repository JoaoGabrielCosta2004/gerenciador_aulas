package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Nota;
import br.edu.ifpb.es.daw.todo.rest.dto.NotaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.NotaResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    public Nota from(NotaRequestDTO dto) {
        return Nota.builder()
                .descricao(dto.descricao())
                .valor(dto.valor())
                .build();
    }

    public NotaResponseDTO from(Nota nota) {
        return NotaResponseDTO.builder()
                .id(nota.getId())
                .descricao(nota.getDescricao())
                .valor(nota.getValor())
                .alunoId(nota.getAluno() != null ? nota.getAluno().getId() : null)
                .disciplinaId(nota.getDisciplina() != null ? nota.getDisciplina().getId() : null)
                .materialId(nota.getMaterial() != null ? nota.getMaterial().getId() : null)
                .build();
    }
}
