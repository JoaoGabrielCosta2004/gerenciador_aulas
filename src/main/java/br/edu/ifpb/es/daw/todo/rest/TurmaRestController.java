package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.TurmaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.TurmaResponseDTO;
import br.edu.ifpb.es.daw.todo.service.TurmaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaRestController {

    private final TurmaService service;

    public TurmaRestController(TurmaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TurmaResponseDTO salvar(@RequestBody TurmaRequestDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<TurmaResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public TurmaResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public TurmaResponseDTO atualizar(@PathVariable Long id, @RequestBody TurmaRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}