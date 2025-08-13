package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.NotaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.NotaResponseDTO;
import br.edu.ifpb.es.daw.todo.service.NotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaRestController {

    private final NotaService service;

    public NotaRestController(NotaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<NotaResponseDTO> criar(@RequestBody NotaRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<NotaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody NotaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
