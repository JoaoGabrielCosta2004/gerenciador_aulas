package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.dao.impl.AlunosDAOImpl;
import br.edu.ifpb.es.daw.dao.TurmaDAO;
import br.edu.ifpb.es.daw.dao.impl.TurmaDAOImpl;
import br.edu.ifpb.es.daw.entities.Aluno;
import br.edu.ifpb.es.daw.entities.Turma;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class MainAlunoSave {
    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            AlunoDAO alunoDao = new AlunosDAOImpl(emf);
            TurmaDAO turmaDao = new TurmaDAOImpl(emf);

            Turma turma = turmaDao.getByID(1L);

                Aluno aluno = new Aluno();
                aluno.setNome(System.nanoTime() + "João Gabriel Costa");
                aluno.setDataNascimento(LocalDate.of(2015, 1, 1));
                aluno.setMatricula(System.nanoTime() + "20250510");

                aluno.setTurma(turma);

                alunoDao.save(aluno);

                System.out.println("Aluno salvo com sucesso!");
        }
    }
}
