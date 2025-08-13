package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Aula;
import br.edu.ifpb.es.daw.todo.repository.ProfessorRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.AulaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AulaResponseDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AulaMapper {

    public Aula from(AulaRequestDTO dto, ProfessorRepository professorRepository) {
        Aula aula = new Aula();
        aula.setConteudo(dto.conteudo());
        aula.setQuantidadeFalta(dto.quantidadeFalta() != null ? dto.quantidadeFalta() : 0);
        aula.setData(dto.data());

        professorRepository.findById(dto.professorId())
                .ifPresent(aula::setProfessor);

        return aula;
    }

    public AulaResponseDTO from(Aula aula) {
        return AulaResponseDTO.builder()
                .id(aula.getId())
                .conteudo(aula.getConteudo())
                .data(aula.getData() != null ? aula.getData().toString() : null)
                .quantidadeFalta(aula.getQuantidadeFalta())
                .professorId(aula.getProfessor() != null ? aula.getProfessor().getId() : null)
                .alunoIds(aula.getAlunos().stream().map(a -> a.getId()).collect(Collectors.toSet()))
                .build();
    }
}
