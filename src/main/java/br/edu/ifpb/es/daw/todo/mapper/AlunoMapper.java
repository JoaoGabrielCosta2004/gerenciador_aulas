package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.rest.dto.AlunoRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.TurmaResponseDTO;
import br.edu.ifpb.es.daw.todo.model.Aluno;
import br.edu.ifpb.es.daw.todo.model.Turma;
import br.edu.ifpb.es.daw.todo.repository.TurmaRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    private final TurmaRepository turmaRepository;

    public AlunoMapper(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public Aluno from(AlunoRequestDTO dto) {
        Turma turma = turmaRepository.findById(dto.turmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        return Aluno.builder()
                .nome(dto.nome())
                .matricula(dto.matricula())
                .email(dto.email())
                .dataNascimento(dto.dataNascimento())
                .turma(turma)
                .build();
    }

    public AlunoResponseDTO from(Aluno entity) {
        return AlunoResponseDTO.builder()
                .lookupId(entity.getLookupId())
                .nome(entity.getNome())
                .matricula(entity.getMatricula())
                .email(entity.getEmail())
                .dataNascimento(entity.getDataNascimento())
                .turma(TurmaResponseDTO.builder()
                        .lookupId(entity.getTurma().getLookupId())
                        .nome(entity.getTurma().getNome())
                        .ano(entity.getTurma().getAno())
                        .build())
                .build();
    }
}
