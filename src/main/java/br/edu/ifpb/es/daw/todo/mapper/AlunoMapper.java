package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Aluno;
import br.edu.ifpb.es.daw.todo.model.Aula;
import br.edu.ifpb.es.daw.todo.model.Turma;
import br.edu.ifpb.es.daw.todo.repository.AulaRepository;
import br.edu.ifpb.es.daw.todo.repository.TurmaRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.TurmaResponseDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AlunoMapper {

    private final TurmaRepository turmaRepository;
    private final AulaRepository aulaRepository;

    public AlunoMapper(TurmaRepository turmaRepository, AulaRepository aulaRepository) {
        this.turmaRepository = turmaRepository;
        this.aulaRepository = aulaRepository;
    }

    public Aluno from(AlunoRequestDTO dto) {
        Turma turma = turmaRepository.findById(dto.turmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        Set<Aula> aulas = new HashSet<>();
        if (dto.aulaIds() != null) {
            dto.aulaIds().forEach(id -> {
                Aula aula = aulaRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Aula não encontrada: " + id));
                aulas.add(aula);
            });
        }

        Aluno aluno = Aluno.builder()
                .nome(dto.nome())
                .matricula(dto.matricula())
                .email(dto.email())
                .dataNascimento(dto.dataNascimento())
                .turma(turma)
                .aulas(aulas)
                .build();

        // adiciona aluno nas aulas
        aulas.forEach(aula -> aula.getAlunos().add(aluno));

        return aluno;
    }

    public AlunoResponseDTO from(Aluno entity) {
        Set<Long> aulaIds = new HashSet<>();
        if (entity.getAulas() != null) {
            entity.getAulas().forEach(aula -> aulaIds.add(aula.getId()));
        }

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
                .aulaIds(aulaIds)
                .build();
    }
}
