package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.DisciplinaDAO;
import br.edu.ifpb.es.daw.dao.impl.DisciplinaDAOImpl;
import br.edu.ifpb.es.daw.entities.Disciplina;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainDisciplinaSave {
    public static void main(String[] args) throws DawException {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){
            DisciplinaDAO dao = new DisciplinaDAOImpl(emf);
            Disciplina disciplina = new Disciplina();

            disciplina.setNome("Matematica");


            System.out.println("Disciplina salvo com sucesso!");

            dao.save(disciplina);
        }
    }
}
