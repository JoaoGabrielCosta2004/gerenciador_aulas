package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ProfessorDAO;
import br.edu.ifpb.es.daw.dao.impl.ProfessorDAOImpl;
import br.edu.ifpb.es.daw.entities.Professor;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainProfessorDeleteAll {
    public static void main(String[] args) throws DawException {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            ProfessorDAO dao = new ProfessorDAOImpl(emf);        
            for (Professor prof : dao.getAll()) {
                dao.delete(prof.getId());
            }
            System.out.println("Todos os professores foram deletados com sucesso!");

        }
	}
}
