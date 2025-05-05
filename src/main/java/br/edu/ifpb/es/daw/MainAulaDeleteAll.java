package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.dao.impl.AulaDAOImpl;
import br.edu.ifpb.es.daw.entities.Aula;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainAulaDeleteAll {
    public static void main(String[] args) throws DawException {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            AulaDAO dao = new AulaDAOImpl(emf);
            for (Aula aula : dao.getAll()) {
                dao.delete(aula.getId());
            }
            System.out.println("Todas as aulas foram deletadas com sucesso!");

        } 
	}
}
