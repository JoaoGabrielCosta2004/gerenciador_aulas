package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.dao.impl.AulaDAOImpl;
import br.edu.ifpb.es.daw.entities.Aula;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainAulaSave {
    public static void main(String[] args) throws DawException {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){
            AulaDAO dao = new AulaDAOImpl(emf);
            Aula aula = new Aula();

            aula.setData("03/05/2025");
            aula.setConteudo("Aplicação de prova");

            System.out.println("Aula salvo com sucesso!");

            dao.save(aula);
        }
    }
}
