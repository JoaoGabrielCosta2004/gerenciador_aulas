package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.rest.dto.TurmaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.TurmaResponseDTO;
import br.edu.ifpb.es.daw.todo.mapper.TurmaMapper;
import br.edu.ifpb.es.daw.todo.model.Turma;
import br.edu.ifpb.es.daw.todo.repository.TurmaRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.TurmaRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository repository;
    private final TurmaMapper mapper;

    public TurmaService(TurmaRepository repository, TurmaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TurmaResponseDTO salvar(TurmaRequestDTO dto) {
        Turma turma = mapper.from(dto);
        return mapper.from(repository.save(turma));
    }

    public List<TurmaResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::from)
                .toList();
    }

    public TurmaResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::from)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
    }

    public TurmaResponseDTO atualizar(Long id, TurmaRequestDTO dto) {
        Turma turma = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        turma.setNome(dto.nome());
        turma.setAno(dto.ano());

        return mapper.from(repository.save(turma));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
