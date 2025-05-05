package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.TurmaDAO;
import br.edu.ifpb.es.daw.dao.impl.TurmaDAOImpl;
import br.edu.ifpb.es.daw.entities.Turma;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainTurmaDeleteAll {
    public static void main(String[] args) throws DawException {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            TurmaDAO dao = new TurmaDAOImpl(emf);        
            for (Turma turma : dao.getAll()) {
                dao.delete(turma.getId());
            }
            System.out.println("Todas as turmas foram deletadas com sucesso!");

        }
	}
}
