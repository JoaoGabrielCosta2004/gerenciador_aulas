package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.TurmaDAO;
import br.edu.ifpb.es.daw.dao.impl.TurmaDAOImpl;
import br.edu.ifpb.es.daw.entities.Turma;

public class MainTurmaDeleteAll {
    public static void main(String[] args) throws PersistenciaDawException {
        TurmaDAO dao = new TurmaDAOImpl();
        List<Turma> lista = dao.getAll();

        for (Turma turma : lista) {
            dao.delete(turma.getId());
        }
        dao.restartSequence();
    }
}
