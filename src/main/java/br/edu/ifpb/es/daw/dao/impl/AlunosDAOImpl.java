package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.AlunosDAO;
import jakarta.persistence.EntityManagerFactory;

public class AlunosDAOImpl extends AbstractDAOImpl<Alunos, Long> implements AlunosDAO {
    public AulaDAOImpl(EntityManagerFactory emf) {
        super(Alunos.class, emf);
    }
}
