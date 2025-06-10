package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.DisciplinaDAO;
import br.edu.ifpb.es.daw.dao.ProfessorDAO;
import br.edu.ifpb.es.daw.dao.impl.DisciplinaDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.ProfessorDAOImpl;
import br.edu.ifpb.es.daw.entities.Disciplina;
import br.edu.ifpb.es.daw.entities.Professor;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainProfessorSave {
    public static void main(String[] args) throws DawException {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){
            ProfessorDAO dao = new ProfessorDAOImpl(emf);
            DisciplinaDAO disciplinaDao = new DisciplinaDAOImpl(emf);

            Professor professor = new Professor();
            Disciplina disciplina = disciplinaDao.getByID(1L);

            professor.setNome("João da Silva");
            professor.setEmail(System.nanoTime() + "joao.silva@exemplo.com");
            professor.setSenha("senhaSegura123");

            professor.setDisciplina(disciplina);

            dao.save(professor);

            System.out.println("Professor salvo com sucesso!");

        }
    }
}
