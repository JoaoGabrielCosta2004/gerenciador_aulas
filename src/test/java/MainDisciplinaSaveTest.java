import static org.junit.jupiter.api.Assertions.*;

import br.edu.ifpb.es.daw.DawException;
import br.edu.ifpb.es.daw.dao.DisciplinaDAO;
import br.edu.ifpb.es.daw.dao.impl.DisciplinaDAOImpl;
import br.edu.ifpb.es.daw.entities.Disciplina;
import org.junit.jupiter.api.*;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class MainDisciplinaSaveTest {

    private static EntityManagerFactory emf;

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

    @Test
    public void deveSalvarDisciplinaComSucesso() throws DawException {
        DisciplinaDAO dao = new DisciplinaDAOImpl(emf);
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(System.nanoTime()+"Matematica");

        dao.save(disciplina);

        assertNotNull(disciplina.getId(), "O ID da disciplina não pode ser nulo após salvar.");
    }
}
