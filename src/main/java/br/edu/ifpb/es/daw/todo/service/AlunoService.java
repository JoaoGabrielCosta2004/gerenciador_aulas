package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.model.Aluno;
import br.edu.ifpb.es.daw.todo.model.Aula;
import br.edu.ifpb.es.daw.todo.repository.AlunoRepository;
import br.edu.ifpb.es.daw.todo.repository.AulaRepository;
import br.edu.ifpb.es.daw.todo.repository.TurmaRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoResponseDTO;
import br.edu.ifpb.es.daw.todo.mapper.AlunoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AlunoService {

    private final AlunoRepository repository;
    private final TurmaRepository turmaRepository;
    private final AulaRepository aulaRepository;
    private final AlunoMapper mapper;

    public AlunoService(AlunoRepository repository, TurmaRepository turmaRepository, AulaRepository aulaRepository, AlunoMapper mapper) {
        this.repository = repository;
        this.turmaRepository = turmaRepository;
        this.aulaRepository = aulaRepository;
        this.mapper = mapper;
    }

    public AlunoResponseDTO salvar(AlunoRequestDTO dto) {
        Aluno aluno = mapper.from(dto);
        return mapper.from(repository.save(aluno));
    }

    public List<AlunoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::from)
                .toList();
    }
    public Page<AlunoResponseDTO> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::from); // usa o AlunoMapper
    }

    public Page<AlunoResponseDTO> filtrarPorNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(mapper::from); // usa o AlunoMapper
    }

    public Page<AlunoResponseDTO> filtrarPorEmail(String email, Pageable pageable) {
        return repository.findByEmailContainingIgnoreCase(email, pageable)
                .map(mapper::from); // usa o AlunoMapper
    }


    public AlunoResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::from)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }

    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto) {
        Aluno aluno = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        aluno.setNome(dto.nome());
        aluno.setMatricula(dto.matricula());
        aluno.setEmail(dto.email());
        aluno.setDataNascimento(dto.dataNascimento());
        aluno.setTurma(turmaRepository.findById(dto.turmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada")));

        // Atualiza aulas
        Set<Aula> aulas = new HashSet<>();
        if (dto.aulaIds() != null) {
            dto.aulaIds().forEach(aulaId -> {
                Aula aula = aulaRepository.findById(aulaId)
                        .orElseThrow(() -> new IllegalArgumentException("Aula não encontrada: " + aulaId));
                aulas.add(aula);
            });
        }
        aluno.setAulas(aulas);
        aulas.forEach(aula -> aula.getAlunos().add(aluno));

        return mapper.from(repository.save(aluno));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
