package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.NotaDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.NotaDAOImpl;
import br.edu.ifpb.es.daw.entities.Nota;

public class MainNotaSave {
    public static void main(String[] args) throws PersistenciaDawException {
        NotaDAO dao = new NotaDAOImpl();
        Nota nota = new Nota();

        nota.setValor(7.0);
        nota.setId_aluno(5);
        nota.setId_disciplina(2);
        nota.setDescricao("Prova de Matematica aplicada dia 13/05/2025");
        nota.setId_material(1);

        System.out.println("Nota salvo com sucesso!");

        dao.save(nota);
    }
}
