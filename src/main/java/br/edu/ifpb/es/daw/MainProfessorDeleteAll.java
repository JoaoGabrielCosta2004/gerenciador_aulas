package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.ProfessorDAO;
import br.edu.ifpb.es.daw.dao.impl.ProfessorDAOImpl;
import br.edu.ifpb.es.daw.entities.Professor;

public class MainProfessorDeleteAll {
    public static void main(String[] args) throws PersistenciaDawException {
        ProfessorDAO dao = new ProfessorDAOImpl();
        List<Professor> lista = dao.getAll();

        for (Professor prof : lista) {
            dao.delete(prof.getId());
        }
        dao.restartSequence();
    }
}
