package br.edu.ifpb.es.daw.todo.repository;

import br.edu.ifpb.es.daw.todo.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
    // Podemos adicionar métodos customizados se necessário
}
