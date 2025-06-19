package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ProjetoDAO;
import br.edu.ifpb.es.daw.dao.impl.ProjetoDAOImpl;
import br.edu.ifpb.es.daw.entities.Projeto;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainProjetoDeleteAll {

	public static void main(String[] args) throws DawException {
		try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
			ProjetoDAO projetoDAO = new ProjetoDAOImpl(emf);
			List<Projeto> projetos = projetoDAO.getAll();
			for (Projeto projeto : projetos) {
				projetoDAO.delete(projeto.getId());
			}
		}
	}
}
