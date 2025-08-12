package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.model.Aluno;
import br.edu.ifpb.es.daw.todo.repository.AlunoRepository;
import br.edu.ifpb.es.daw.todo.repository.TurmaRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoResponseDTO;
import br.edu.ifpb.es.daw.todo.mapper.AlunoMapper;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository repository;
    private final TurmaRepository turmaRepository;
    private final AlunoMapper mapper;

    public AlunoService(AlunoRepository repository, TurmaRepository turmaRepository, AlunoMapper mapper) {
        this.repository = repository;
        this.turmaRepository = turmaRepository;
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

        return mapper.from(repository.save(aluno));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}