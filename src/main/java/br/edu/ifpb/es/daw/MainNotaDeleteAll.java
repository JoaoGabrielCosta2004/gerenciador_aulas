package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.NotaDAO;
import br.edu.ifpb.es.daw.dao.impl.*;
import br.edu.ifpb.es.daw.entities.Nota;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainNotaDeleteAll {
    public static void main(String[] args) throws DawException {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            NotaDAO dao = new NotaDAOImpl(emf);        
            for (Nota nota : dao.getAll()) {
                dao.delete(nota.getId());
            }
            System.out.println("Todas as notas foram deletadas com sucesso!");

        } 
	}
}
