package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.TurmaDAO;
import br.edu.ifpb.es.daw.dao.impl.TurmaDAOImpl;
import br.edu.ifpb.es.daw.entities.Turma;

public class MainTurmaSave {
    public static void main(String[] args) throws PersistenciaDawException {
        TurmaDAO dao = new TurmaDAOImpl();
        Turma turma = new Turma();

        turma.setNome("2° ano A 2025");
        turma.setAno(2);

        System.out.println("Turma salvo com sucesso!");

        dao.save(turma);

    }
}
