package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.model.Aluno;
import br.edu.ifpb.es.daw.todo.repository.AlunoRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoResponseDTO;
import br.edu.ifpb.es.daw.todo.mapper.AlunoMapper;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoSalvarRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
    }

    @Transactional
    public AlunoResponseDTO salvar(AlunoSalvarRequestDTO dto) {
        Aluno aluno = alunoMapper.from(dto);
        Aluno alunoCriado = alunoRepository.save(aluno);
        return alunoMapper.from(alunoCriado);
    }

    public List<AlunoResponseDTO> recuperarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(alunoMapper::from)
                .toList();
    }

    private Aluno ensureExists(Long id) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(id);
        return alunoOpt.orElseThrow(() -> new IllegalArgumentException(String.format("Aluno com ID '%d' não encontrado", id)));
    }

    public AlunoResponseDTO buscarPorId(Long id) {
        Aluno aluno = ensureExists(id);
        return alunoMapper.from(aluno);
    }

    @Transactional
    public AlunoResponseDTO atualizar(Long id, AlunoSalvarRequestDTO dto) {
        Aluno alunoExistente = ensureExists(id);
        alunoExistente.setNome(dto.getNome());
        alunoExistente.setMatricula(dto.getMatricula());
        alunoExistente.setEmail(dto.getEmail());
        Aluno alunoAtualizado = alunoRepository.save(alunoExistente);
        return alunoMapper.from(alunoAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(id);
        alunoOpt.ifPresent(alunoRepository::delete);
    }
}