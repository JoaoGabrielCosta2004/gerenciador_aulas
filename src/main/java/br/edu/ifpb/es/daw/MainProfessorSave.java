package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.ProfessorDAO;
import br.edu.ifpb.es.daw.dao.impl.ProfessorDAOImpl;
import br.edu.ifpb.es.daw.entities.Professor;

public class MainProfessorSave {
    public static void main(String[] args) throws PersistenciaDawException {
        ProfessorDAO dao = new ProfessorDAOImpl();

        Professor professor = new Professor();

        professor.setNome("João da Silva");
        professor.setEmail(System.nanoTime() + "joao.silva@exemplo.com");
        professor.setSenha("senhaSegura123");

        System.out.println("Professor salvo com sucesso!");

        dao.save(professor);

    }
}
