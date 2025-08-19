package br.edu.ifpb.es.daw.todo.repository;

import br.edu.ifpb.es.daw.todo.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    // Busca por lookupId
    Aluno findByLookupId(UUID lookupId);

    // Filtro por nome (contendo, ignorando maiúsculas/minúsculas)
    Page<Aluno> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    // Caso queira também por email
    Page<Aluno> findByEmailContainingIgnoreCase(String email, Pageable pageable);
}
