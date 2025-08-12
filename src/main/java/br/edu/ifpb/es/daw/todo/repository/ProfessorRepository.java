package br.edu.ifpb.es.daw.todo.repository;


import br.edu.ifpb.es.daw.todo.model.Professor;
import br.edu.ifpb.es.daw.todo.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByLookupId(UUID lookupId);

    Optional<Professor> findByEmail(String email);

    boolean existsByEmail(String email);
}
