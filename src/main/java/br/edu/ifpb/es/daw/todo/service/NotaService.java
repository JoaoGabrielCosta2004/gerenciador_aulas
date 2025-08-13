package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.model.Aluno;
import br.edu.ifpb.es.daw.todo.model.Disciplina;
import br.edu.ifpb.es.daw.todo.model.Material;
import br.edu.ifpb.es.daw.todo.model.Nota;
import br.edu.ifpb.es.daw.todo.repository.AlunoRepository;
import br.edu.ifpb.es.daw.todo.repository.DisciplinaRepository;
import br.edu.ifpb.es.daw.todo.repository.MaterialRepository;
import br.edu.ifpb.es.daw.todo.repository.NotaRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.NotaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.NotaResponseDTO;
import br.edu.ifpb.es.daw.todo.mapper.NotaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaService {

    private final NotaRepository repository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final MaterialRepository materialRepository;
    private final NotaMapper mapper;

    public NotaService(NotaRepository repository,
                       AlunoRepository alunoRepository,
                       DisciplinaRepository disciplinaRepository,
                       MaterialRepository materialRepository,
                       NotaMapper mapper) {
        this.repository = repository;
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.materialRepository = materialRepository;
        this.mapper = mapper;
    }

    public NotaResponseDTO criar(NotaRequestDTO dto) {
        Nota nota = mapper.from(dto);

        if (dto.alunoId() != null) {
            Aluno aluno = alunoRepository.findById(dto.alunoId())
                    .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
            nota.setAluno(aluno);
        }

        if (dto.disciplinaId() != null) {
            Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId())
                    .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
            nota.setDisciplina(disciplina);
        }

        if (dto.materialId() != null) {
            Material material = materialRepository.findById(dto.materialId())
                    .orElseThrow(() -> new IllegalArgumentException("Material não encontrado"));
            nota.setMaterial(material);
        }

        return mapper.from(repository.save(nota));
    }

    public List<NotaResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::from)
                .collect(Collectors.toList());
    }

    public NotaResponseDTO atualizar(Long id, NotaRequestDTO dto) {
        Nota nota = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        nota.setDescricao(dto.descricao());
        nota.setValor(dto.valor());

        if (dto.alunoId() != null) {
            Aluno aluno = alunoRepository.findById(dto.alunoId())
                    .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
            nota.setAluno(aluno);
        }

        if (dto.disciplinaId() != null) {
            Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId())
                    .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
            nota.setDisciplina(disciplina);
        }

        if (dto.materialId() != null) {
            Material material = materialRepository.findById(dto.materialId())
                    .orElseThrow(() -> new IllegalArgumentException("Material não encontrado"));
            nota.setMaterial(material);
        }

        return mapper.from(repository.save(nota));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
