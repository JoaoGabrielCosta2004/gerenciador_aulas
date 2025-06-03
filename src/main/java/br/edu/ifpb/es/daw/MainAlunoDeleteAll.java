package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.AlunosDAOImpl;
import br.edu.ifpb.es.daw.entities.Aluno;

public class MainAlunoDeleteAll {
    public static void main(String[] args) throws PersistenciaDawException {
        AlunoDAO dao = new AlunosDAOImpl();
        List<Aluno> lista = dao.getAll();

        for (Aluno aluno : lista) {
            dao.delete(aluno.getId());
        }
                dao.restartSequence();
    }
}
