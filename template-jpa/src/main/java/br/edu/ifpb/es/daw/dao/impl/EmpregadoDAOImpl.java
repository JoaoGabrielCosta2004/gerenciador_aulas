package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.EmpregadoDAO;
import br.edu.ifpb.es.daw.entities.Empregado;
import jakarta.persistence.EntityManagerFactory;

public class EmpregadoDAOImpl extends AbstractDAOImpl<Empregado, Long> implements EmpregadoDAO {

    public EmpregadoDAOImpl(EntityManagerFactory emf) {
        super(Empregado.class, emf);
    }

    // Adicione métodos específicos para Empregado aqui, se necessário
}
