package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.dao.impl.AulaDAOImpl;
import br.edu.ifpb.es.daw.dao.ProfessorDAO;
import br.edu.ifpb.es.daw.dao.impl.ProfessorDAOImpl;
import br.edu.ifpb.es.daw.entities.Aula;
import br.edu.ifpb.es.daw.entities.Professor;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainAulaSave {
    public static void main(String[] args) throws DawException {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){
            AulaDAO aulaDAO = new AulaDAOImpl(emf);
            ProfessorDAO professorDAO = new ProfessorDAOImpl(emf);

            Professor professor = professorDAO.getByID(6L);


            Aula aula = new Aula();
            aula.setData("03/05/2025");
            aula.setConteudo("pratica no lab");
            aula.setQuantidadeFalta(10);

            aula.setProfessor(professor);

            aulaDAO.save(aula);

            System.out.println("Aula salva com sucesso!");
        }
    }
}
