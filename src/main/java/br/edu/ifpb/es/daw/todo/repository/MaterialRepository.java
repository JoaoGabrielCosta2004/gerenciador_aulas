package br.edu.ifpb.es.daw.todo.repository;

import br.edu.ifpb.es.daw.todo.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    // Métodos customizados podem ser adicionados aqui
}
