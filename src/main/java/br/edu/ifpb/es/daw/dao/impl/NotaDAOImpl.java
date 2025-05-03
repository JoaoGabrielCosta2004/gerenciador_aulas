package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.NotaDAO;
import jakarta.persistence.EntityManagerFactory;

public class NotaDAOImpl extends AbstractDAOImpl<Nota, Long> implements NotaDAO {
    public NotaDAOImpl(EntityManagerFactory emf) {
        super(Nota.class, emf);
    }
}
