package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.mapper.DisciplinaMapper;
import br.edu.ifpb.es.daw.todo.model.Disciplina;
import br.edu.ifpb.es.daw.todo.repository.DisciplinaRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.DisciplinaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.DisciplinaResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DisciplinaService {

    private final DisciplinaRepository repository;
    private final DisciplinaMapper mapper;

    public DisciplinaService(DisciplinaRepository repository, DisciplinaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<DisciplinaResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::from)
                .collect(Collectors.toList());
    }

    public DisciplinaResponseDTO buscarPorId(Long id) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
        return mapper.from(disciplina);
    }

    public DisciplinaResponseDTO criar(DisciplinaRequestDTO dto) {
        Disciplina disciplina = mapper.from(dto);
        Disciplina salvo = repository.save(disciplina);
        return mapper.from(salvo);
    }

    public DisciplinaResponseDTO atualizar(Long id, DisciplinaRequestDTO dto) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        disciplina.setNome(dto.nome());
        Disciplina atualizado = repository.save(disciplina);
        return mapper.from(atualizado);
    }

    public void deletar(Long id) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
        repository.delete(disciplina);
    }
}