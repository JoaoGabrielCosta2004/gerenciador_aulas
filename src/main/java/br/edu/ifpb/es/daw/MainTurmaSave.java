package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.TurmaDAO;
import br.edu.ifpb.es.daw.dao.impl.TurmaDAOImpl;
import br.edu.ifpb.es.daw.entities.Turma;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainTurmaSave {
    public static void main(String[] args) throws DawException {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){
            TurmaDAO dao = new TurmaDAOImpl(emf);
            Turma turma = new Turma();

            turma.setNome("5° ano B 2025");
            turma.setAno(5);

            System.out.println("Turma salvo com sucesso!");

            dao.save(turma);
        }
    }
}

