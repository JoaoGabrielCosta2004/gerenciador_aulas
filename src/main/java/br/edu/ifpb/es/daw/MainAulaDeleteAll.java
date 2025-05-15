package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.AulaDAOImpl;
import br.edu.ifpb.es.daw.entities.Aula;

public class MainAulaDeleteAll {
    public static void main(String[] args) throws PersistenciaDawException {
        AulaDAO dao = new AulaDAOImpl();
        List<Aula> lista = dao.getAll();

        for (Aula aula : lista) {
            dao.delete(aula.getId());
        }
    }
}
