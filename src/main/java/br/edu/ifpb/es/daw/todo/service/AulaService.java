package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.mapper.AulaMapper;
import br.edu.ifpb.es.daw.todo.model.Aula;
import br.edu.ifpb.es.daw.todo.repository.AulaRepository;
import br.edu.ifpb.es.daw.todo.repository.ProfessorRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.AulaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AulaResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaService {

    private final AulaRepository repository;
    private final AulaMapper mapper;
    private final ProfessorRepository professorRepository;

    public AulaService(AulaRepository repository, AulaMapper mapper, ProfessorRepository professorRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.professorRepository = professorRepository;
    }

    public AulaResponseDTO criar(AulaRequestDTO dto) {
        Aula aula = mapper.from(dto, professorRepository);
        repository.save(aula);
        return mapper.from(aula);
    }

    public List<AulaResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::from)
                .collect(Collectors.toList());
    }

    public AulaResponseDTO atualizar(Long id, AulaRequestDTO dto) {
        Aula aula = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aula não encontrada"));
        aula.setConteudo(dto.conteudo());
        aula.setQuantidadeFalta(dto.quantidadeFalta());
        aula.setData(dto.data());
        professorRepository.findById(dto.professorId())
                .ifPresent(aula::setProfessor);
        repository.save(aula);
        return mapper.from(aula);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
