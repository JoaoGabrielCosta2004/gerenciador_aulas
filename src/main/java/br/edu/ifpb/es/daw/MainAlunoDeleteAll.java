package br.edu.ifpb.es.daw;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.dao.impl.AlunosDAOImpl;
import br.edu.ifpb.es.daw.entities.Aluno;

public class MainAlunoDeleteAll {
    public static void main(String[] args) throws DawException {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            AlunoDAO dao = new AlunosDAOImpl(emf);        
            for (Aluno aluno : dao.getAll()) {
                dao.delete(aluno.getId());
            }
            System.out.println("Todos os alunos foram deletados com sucesso!");

        } 
	}
}
