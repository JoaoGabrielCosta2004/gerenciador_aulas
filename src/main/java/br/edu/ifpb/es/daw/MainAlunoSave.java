package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.AlunosDAOImpl;
import br.edu.ifpb.es.daw.entities.Aluno;

import java.time.LocalDate;

public class MainAlunoSave {
    public static void main(String[] args) throws PersistenciaDawException {
                AlunoDAO dao = new AlunosDAOImpl();

                Aluno aluno = new Aluno();
                aluno.setNome("João Gabriel Costa");
                aluno.setDataNascimento(LocalDate.of(2015, 1, 1));
                aluno.setMatricula(System.nanoTime() + "20250510");

                    dao.save(aluno);
                System.out.println("Aluno salvo com sucesso!");

            
    }
}
