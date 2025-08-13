package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.AulaRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AulaResponseDTO;
import br.edu.ifpb.es.daw.todo.service.AulaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaRestController {

    private final AulaService aulaService;

    public AulaRestController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping
    public ResponseEntity<AulaResponseDTO> criar(@RequestBody AulaRequestDTO dto) {
        return ResponseEntity.ok(aulaService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<AulaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(aulaService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AulaResponseDTO> atualizar(@PathVariable Long id, @RequestBody AulaRequestDTO dto) {
        return ResponseEntity.ok(aulaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        aulaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
