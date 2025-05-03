package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.AulaDAO;
import jakarta.persistence.EntityManagerFactory;

public class AulaDAOImpl extends AbstractDAOImpl<Aula, Long> implements AulaDAO {
    public AulaDAOImpl(EntityManagerFactory emf) {
        super(Aula.class, emf);
    }
}
