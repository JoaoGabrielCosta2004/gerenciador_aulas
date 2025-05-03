package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.DisciplinaDAO;
import br.edu.ifpb.es.daw.entities.Disciplina;
import jakarta.persistence.EntityManagerFactory;

public class DisciplinaDAOImpl extends AbstractDAOImpl<Disciplina, Long> implements DisciplinaDAO {
    public DisciplinaDAOImpl(EntityManagerFactory emf) {
        super(Disciplina.class, emf);
    }
}
