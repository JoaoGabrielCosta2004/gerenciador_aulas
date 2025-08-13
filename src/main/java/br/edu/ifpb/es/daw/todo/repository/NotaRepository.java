package br.edu.ifpb.es.daw.todo.repository;

import br.edu.ifpb.es.daw.todo.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
}
