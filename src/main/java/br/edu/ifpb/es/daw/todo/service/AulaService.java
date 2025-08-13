package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.model.Aula;
import br.edu.ifpb.es.daw.todo.repository.AulaRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.AulaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AulaResponseDTO;
import br.edu.ifpb.es.daw.todo.mapper.AulaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaService {

    private final AulaRepository repository;
    private final AulaMapper mapper;

    public AulaService(AulaRepository repository, AulaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AulaResponseDTO salvar(AulaRequestDTO dto) {
        Aula aula = mapper.from(dto);
        return mapper.from(repository.save(aula));
    }

    public List<AulaResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::from)
                .toList();
    }

    public AulaResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::from)
                .orElseThrow(() -> new IllegalArgumentException("Aula não encontrada"));
    }

    public AulaResponseDTO atualizar(Long id, AulaRequestDTO dto) {
        Aula aula = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aula não encontrada"));

        Aula atualizado = mapper.from(dto);
        aula.setConteudo(atualizado.getConteudo());
        aula.setData(atualizado.getData());
        aula.setProfessor(atualizado.getProfessor());
        aula.setAlunos(atualizado.getAlunos());

        return mapper.from(repository.save(aula));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
