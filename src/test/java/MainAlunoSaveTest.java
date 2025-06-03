import static org.junit.jupiter.api.Assertions.*;

import br.edu.ifpb.es.daw.DawException;
import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.dao.TurmaDAO;
import br.edu.ifpb.es.daw.dao.impl.AlunosDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.TurmaDAOImpl;
import br.edu.ifpb.es.daw.entities.Aluno;
import br.edu.ifpb.es.daw.entities.Turma;
import org.junit.jupiter.api.*;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MainAlunoSaveTest {

    private static EntityManagerFactory emf;
    private AlunoDAO alunoDao;
    private TurmaDAO turmaDao;

    @BeforeAll
    public static void setupEntityManagerFactory() {
        Map<String, String> props = new HashMap<>();
        props.put("javax.persistence.jdbc.url", "jdbc:postgresql://ep-cool-leaf-a5yeb5g0-pooler.us-east-2.aws.neon.tech/neondb");
        props.put("javax.persistence.jdbc.user", "neondb_owner");
        props.put("javax.persistence.jdbc.password", "npg_MXnT53jBrqbl");
        props.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        emf = Persistence.createEntityManagerFactory("daw", props);
    }

    @AfterAll
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    @BeforeEach
    public void setupDAO() {
        alunoDao = new AlunosDAOImpl(emf);
        turmaDao = new TurmaDAOImpl(emf);
    }

    @Test
    public void deveSalvarAlunoComSucesso() throws DawException {
        Turma turma = turmaDao.getByID(1L);
        assertNotNull(turma, "Turma não pode ser nula");

        Aluno aluno = new Aluno();
        aluno.setNome("Julia Xavier Silva");
        aluno.setDataNascimento(LocalDate.of(2015, 1, 1));
        aluno.setMatricula(System.nanoTime() + "20250510");
        aluno.setTurma(turma);

        alunoDao.save(aluno);

        assertNotNull(aluno.getId(), "O ID do aluno não pode ser nulo após salvar.");
    }
}