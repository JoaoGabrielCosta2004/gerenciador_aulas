package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.ProjetoDAO;
import br.edu.ifpb.es.daw.entities.Projeto;
import jakarta.persistence.EntityManagerFactory;

public class ProjetoDAOImpl extends AbstractDAOImpl<Projeto, Long> implements ProjetoDAO {
    public ProjetoDAOImpl(EntityManagerFactory emf) {
        super(Projeto.class, emf);
    }

    // Implementações ou métodos adicionais específicos
}
