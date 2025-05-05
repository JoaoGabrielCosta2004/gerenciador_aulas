package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.DisciplinaDAO;
import br.edu.ifpb.es.daw.dao.impl.DisciplinaDAOImpl;
import br.edu.ifpb.es.daw.entities.Disciplina;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainDisciplinaDeleteAll {
    public static void main(String[] args) throws DawException {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            DisciplinaDAO dao = new DisciplinaDAOImpl(emf);
            for (Disciplina dis : dao.getAll()) {
                dao.delete(dis.getId());
            }
            System.out.println("Todos os professores foram deletados com sucesso!");

        }
	}
}
