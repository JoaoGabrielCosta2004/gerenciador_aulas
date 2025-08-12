package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.ProfessorRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.ProfessorResponseDTO;
import br.edu.ifpb.es.daw.todo.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/professores")
public class ProfessorRestController {

    private final ProfessorService service;

    public ProfessorRestController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{lookupId}")
    public ResponseEntity<ProfessorResponseDTO> buscarPorId(@PathVariable UUID lookupId) {
        return ResponseEntity.ok(service.buscarPorLookupId(lookupId));
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> criar(@RequestBody ProfessorRequestDTO dto) {
        ProfessorResponseDTO criado = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{lookupId}")
    public ResponseEntity<ProfessorResponseDTO> atualizar(
            @PathVariable UUID lookupId,
            @RequestBody ProfessorRequestDTO dto) {
        ProfessorResponseDTO atualizado = service.atualizar(lookupId, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{lookupId}")
    public ResponseEntity<Void> deletar(@PathVariable UUID lookupId) {
        service.deletar(lookupId);
        return ResponseEntity.noContent().build();
    }
}
