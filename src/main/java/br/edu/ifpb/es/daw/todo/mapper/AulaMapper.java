package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Aula;
import br.edu.ifpb.es.daw.todo.model.Aluno;
import br.edu.ifpb.es.daw.todo.model.Professor;
import br.edu.ifpb.es.daw.todo.repository.AlunoRepository;
import br.edu.ifpb.es.daw.todo.repository.ProfessorRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.AulaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AulaResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.ProfessorResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AulaMapper {

    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;

    public AulaMapper(ProfessorRepository professorRepository, AlunoRepository alunoRepository) {
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    public Aula from(AulaRequestDTO dto) {
        Professor professor = professorRepository.findById(dto.professorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        Aula aula = Aula.builder()
                .conteudo(dto.conteudo())
                .professor(professor)
                .data(dto.data())
                .build();

        // Adiciona alunos
        if (dto.alunoIds() != null) {
            Set<Aluno> alunos = dto.alunoIds().stream()
                    .map(id -> alunoRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado: " + id)))
                    .collect(Collectors.toSet());
            aula.setAlunos(alunos);
            alunos.forEach(aluno -> aluno.getAulas().add(aula));
        }

        return aula;
    }

    public AulaResponseDTO from(Aula aula) {
        Set<AlunoResponseDTO> alunosDTO = aula.getAlunos().stream()
                .map(aluno -> AlunoResponseDTO.builder()
                        .lookupId(aluno.getLookupId())
                        .nome(aluno.getNome())
                        .matricula(aluno.getMatricula())
                        .email(aluno.getEmail())
                        .dataNascimento(aluno.getDataNascimento())
                        .turma(null) // opcional, ou criar DTO de turma aqui
                        .build())
                .collect(Collectors.toSet());

        return AulaResponseDTO.builder()
                .id(aula.getId())
                .conteudo(aula.getConteudo())
                .data(aula.getData())
                .quantidadeFalta(aula.getQuantidadeFalta())
                .professor(ProfessorResponseDTO.builder()
                        .lookupId(aula.getProfessor().getLookupId())
                        .nome(aula.getProfessor().getNome())
                        .build())
                .alunos(alunosDTO)
                .build();
    }
}
