package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.DisciplinaDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.DisciplinaDAOImpl;
import br.edu.ifpb.es.daw.entities.Disciplina;

public class MainDisciplinaDeleteAll {
    public static void main(String[] args) throws PersistenciaDawException {
        DisciplinaDAO dao = new DisciplinaDAOImpl();
        List<Disciplina> lista = dao.getAll();

        for (Disciplina dis : lista) {
            dao.delete(dis.getId());
        }
    }
}
