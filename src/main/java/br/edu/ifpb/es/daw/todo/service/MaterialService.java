package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.model.Aula;
import br.edu.ifpb.es.daw.todo.model.Material;
import br.edu.ifpb.es.daw.todo.repository.AulaRepository;
import br.edu.ifpb.es.daw.todo.repository.MaterialRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.MaterialRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.MaterialResponseDTO;
import br.edu.ifpb.es.daw.todo.mapper.MaterialMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository repository;
    private final AulaRepository aulaRepository;
    private final MaterialMapper mapper;

    public MaterialService(MaterialRepository repository, AulaRepository aulaRepository, MaterialMapper mapper) {
        this.repository = repository;
        this.aulaRepository = aulaRepository;
        this.mapper = mapper;
    }

    public MaterialResponseDTO criar(MaterialRequestDTO dto) {
        Material material = mapper.from(dto);

        if (dto.aulaId() != null) {
            Aula aula = aulaRepository.findById(dto.aulaId())
                    .orElseThrow(() -> new IllegalArgumentException("Aula não encontrada"));
            material.setAula(aula);
        }

        return mapper.from(repository.save(material));
    }

    public List<MaterialResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::from)
                .collect(Collectors.toList());
    }

    public MaterialResponseDTO atualizar(Long id, MaterialRequestDTO dto) {
        Material material = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Material não encontrado"));

        material.setTipo(dto.tipo());
        material.setTitulo(dto.titulo());
        material.setLink(dto.link());

        if (dto.aulaId() != null) {
            Aula aula = aulaRepository.findById(dto.aulaId())
                    .orElseThrow(() -> new IllegalArgumentException("Aula não encontrada"));
            material.setAula(aula);
        }

        return mapper.from(repository.save(material));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
