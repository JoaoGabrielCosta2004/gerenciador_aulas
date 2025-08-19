package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.AlunoRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoRequestDTO;
import br.edu.ifpb.es.daw.todo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/alunos")
public class AlunoRestController {

    private final AlunoService service;

    public AlunoRestController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoResponseDTO salvar(@RequestBody AlunoRequestDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<AlunoResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    // Paginação simples
    @GetMapping("/paginado")
    public Page<AlunoResponseDTO> listarPaginado(Pageable pageable) {
        return service.listarPaginado(pageable);
    }

    // Filtragem por nome
    @GetMapping("/filtrar/nome")
    public Page<AlunoResponseDTO> filtrarPorNome(
            @RequestParam String nome,
            Pageable pageable) {
        return service.filtrarPorNome(nome, pageable);
    }

    // Filtragem por email
    @GetMapping("/filtrar/email")
    public Page<AlunoResponseDTO> filtrarPorEmail(
            @RequestParam String email,
            Pageable pageable) {
        return service.filtrarPorEmail(email, pageable);
    }

    @GetMapping("/{id}")
    public AlunoResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AlunoResponseDTO atualizar(@PathVariable Long id, @RequestBody AlunoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}