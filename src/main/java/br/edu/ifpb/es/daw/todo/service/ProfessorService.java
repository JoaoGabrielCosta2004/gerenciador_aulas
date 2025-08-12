package br.edu.ifpb.es.daw.todo.service;


import br.edu.ifpb.es.daw.todo.mapper.ProfessorMapper;
import br.edu.ifpb.es.daw.todo.model.Disciplina;
import br.edu.ifpb.es.daw.todo.model.Professor;
import br.edu.ifpb.es.daw.todo.repository.DisciplinaRepository;
import br.edu.ifpb.es.daw.todo.repository.ProfessorRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.ProfessorRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.ProfessorResponseDTO;
import br.edu.ifpb.es.daw.todo.mapper.ProfessorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@Transactional
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorMapper mapper;

    public ProfessorService(ProfessorRepository professorRepository,
                            DisciplinaRepository disciplinaRepository,
                            ProfessorMapper mapper) {
        this.professorRepository = professorRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.mapper = mapper;
    }

    public List<ProfessorResponseDTO> listarTodos() {
        return professorRepository.findAll()
                .stream()
                .map(mapper::from)
                .toList();
    }

    public ProfessorResponseDTO buscarPorLookupId(UUID lookupId) {
        Professor professor = professorRepository.findByLookupId(lookupId)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        return mapper.from(professor);
    }

    public ProfessorResponseDTO criar(ProfessorRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        Professor professor = mapper.from(dto, disciplina);
        Professor salvo = professorRepository.save(professor);
        return mapper.from(salvo);
    }

    public ProfessorResponseDTO atualizar(UUID lookupId, ProfessorRequestDTO dto) {
        Professor professor = professorRepository.findByLookupId(lookupId)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        mapper.updateProfessorFromDTO(professor, dto, disciplina);

        Professor atualizado = professorRepository.save(professor);
        return mapper.from(atualizado);
    }

    public void deletar(UUID lookupId) {
        Professor professor = professorRepository.findByLookupId(lookupId)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        professorRepository.delete(professor);
    }
}
