package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.AlunoResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.AlunoSalvarRequestDTO;
import br.edu.ifpb.es.daw.todo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoRestController {

    private final AlunoService alunoService;

    @Autowired
    public AlunoRestController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listar() {
        List<AlunoResponseDTO> resultado = alunoService.recuperarTodos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> adicionar(@RequestBody AlunoSalvarRequestDTO dto) {
        AlunoResponseDTO resultado = alunoService.salvar(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);  // Status 201
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> recuperarPorId(@PathVariable Long id) {
        AlunoResponseDTO resultado = alunoService.buscarPorId(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable Long id, @RequestBody AlunoSalvarRequestDTO dto) {
        AlunoResponseDTO resultado = alunoService.atualizar(id, dto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();  // Status 204
    }
}