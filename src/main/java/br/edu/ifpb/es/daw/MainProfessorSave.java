package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ProfessorDAO;
import br.edu.ifpb.es.daw.dao.impl.ProfessorDAOImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainProfessorSave {
    public static void main(String[] args) throws DawException {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){
            ProfessorDAO dao = new ProfessorDAOImpl(emf);
            Professor professor = new Professor();

            professor.setNome("João da Silva");
            professor.setEmail("joao.silva@exemplo.com");
            professor.setSenha("senhaSegura123");

            System.out.println("Professor salvo com sucesso!");

            dao.save(professor);
        }
    }
}
