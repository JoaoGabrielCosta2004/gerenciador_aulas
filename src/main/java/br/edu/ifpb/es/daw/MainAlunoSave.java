package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.AlunosDAOImpl;
import br.edu.ifpb.es.daw.entities.Aluno;

import java.time.LocalDate;

public class MainAlunoSave {
    public static void main(String[] args) throws PersistenciaDawException {


                // Criando o DAO
                AlunoDAO dao = new AlunosDAOImpl();

                // Criando um novo aluno
                Aluno aluno = new Aluno();
                aluno.setNome("abacate");
                aluno.setDataNascimento(LocalDate.of(2015, 1, 1));
                aluno.setMatricula(System.nanoTime() + "20250510");

                // Salvando o aluno no banco de dados
                    dao.save(aluno);
                // Exibindo mensagem de sucesso
                System.out.println("Aluno salvo com sucesso!");

            
    }
}
