package br.edu.ifpb.es.daw.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import br.edu.ifpb.es.daw.dao.impl.AlunosDAOImpl;
import br.edu.ifpb.es.daw.entities.Aluno;

class TestAlunoDao {
    @Test
    void testAdicionar(){
        AlunoDAO dao = new AlunosDAOImpl();
        Aluno aluno = new Aluno();
        aluno.setNome("João Gabriel Costa");
        aluno.setDataNascimento(LocalDate.of(2015, 1, 1));
        aluno.setMatricula(System.nanoTime() + "20250510");
        assertDoesNotThrow(()->{
            dao.save(aluno);
        });
    }
}
