package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.TurmaDAO;
import br.edu.ifpb.es.daw.entities.Turma;
import jakarta.persistence.EntityManagerFactory;

public class TurmaDAOImpl extends AbstractDAOImpl<Turma, Long> implements TurmaDAO {
    public TurmaDAOImpl(EntityManagerFactory emf) {
        super(Turma.class, emf);
    }
}
