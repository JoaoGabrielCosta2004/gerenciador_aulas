package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.ProfessorDAO;
import br.edu.ifpb.es.daw.entities.Professor;
import jakarta.persistence.EntityManagerFactory;

public class ProfessorDAOImpl extends AbstractDAOImpl<Professor, Long> implements ProfessorDAO {
    public ProfessorDAOImpl(EntityManagerFactory emf) {
        super(Professor.class, emf);
    }
}
