package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.NotaDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.NotaDAOImpl;
import br.edu.ifpb.es.daw.entities.Nota;

public class MainNotaDeleteAll {
    public static void main(String[] args) throws PersistenciaDawException {
        NotaDAO dao = new NotaDAOImpl();
        List<Nota> lista = dao.getAll();

        for (Nota nota : lista) {
            dao.delete(nota.getId());
        }
        dao.restartSequence();
    }
}
