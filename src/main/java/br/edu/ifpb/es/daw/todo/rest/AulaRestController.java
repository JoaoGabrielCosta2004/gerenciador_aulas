package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.AulaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AulaResponseDTO;
import br.edu.ifpb.es.daw.todo.service.AulaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaRestController {

    private final AulaService service;

    public AulaRestController(AulaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AulaResponseDTO salvar(@RequestBody AulaRequestDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<AulaResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public AulaResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AulaResponseDTO atualizar(@PathVariable Long id, @RequestBody AulaRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
