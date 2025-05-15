package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.DisciplinaDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.DisciplinaDAOImpl;
import br.edu.ifpb.es.daw.entities.Disciplina;

public class MainDisciplinaSave {
    public static void main(String[] args) throws PersistenciaDawException {
        DisciplinaDAO dao = new DisciplinaDAOImpl();
        Disciplina disciplina = new Disciplina();

        disciplina.setNome("Portugues");


        System.out.println("Disciplina salvo com sucesso!");

        dao.save(disciplina);

    }
}
