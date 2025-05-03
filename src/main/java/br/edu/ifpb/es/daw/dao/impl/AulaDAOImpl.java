package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.entities.Aula;
import jakarta.persistence.EntityManagerFactory;

public class AulaDAOImpl extends AbstractDAOImpl<Aula, Long> implements AulaDAO {
    public AulaDAOImpl(EntityManagerFactory emf) {
        super(Aula.class, emf);
    }
}
