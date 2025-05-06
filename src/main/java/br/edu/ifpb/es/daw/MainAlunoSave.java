package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.dao.impl.AlunosDAOImpl;
import br.edu.ifpb.es.daw.entities.Aluno;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class MainAlunoSave {
    public static void main(String[] args) throws DawException {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){
            AlunoDAO dao = new AlunosDAOImpl(emf);
            Aluno aluno = new Aluno();

            aluno.setNome("Julia Xavier Slva");
            aluno.setDataNascimento(LocalDate.of(2015, 1,1));
            aluno.setMatricula(System.nanoTime() + "20250510");

            System.out.println("Aluno salvo com sucesso!");

            dao.save(aluno);
        }
    }
}
