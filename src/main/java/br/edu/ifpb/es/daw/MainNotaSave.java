package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.NotaDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.NotaDAOImpl;
import br.edu.ifpb.es.daw.entities.Nota;

public class MainNotaSave {
    public static void main(String[] args) throws PersistenciaDawException {
        NotaDAO dao = new NotaDAOImpl();
        Nota nota = new Nota();

        nota.setValor(10.0);
        nota.setDescricao("Prova de matematica aplicada dia 03/05/2025");

        System.out.println("Nota salvo com sucesso!");

        dao.save(nota);
    }
}
