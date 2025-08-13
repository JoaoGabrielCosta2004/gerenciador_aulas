package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.MaterialRequestDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.MaterialResponseDTO;
import br.edu.ifpb.es.daw.todo.service.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialRestController {

    private final MaterialService materialService;

    public MaterialRestController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping
    public ResponseEntity<MaterialResponseDTO> criar(@RequestBody MaterialRequestDTO dto) {
        return ResponseEntity.ok(materialService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MaterialResponseDTO>> listarTodos() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponseDTO> atualizar(@PathVariable Long id, @RequestBody MaterialRequestDTO dto) {
        return ResponseEntity.ok(materialService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        materialService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
