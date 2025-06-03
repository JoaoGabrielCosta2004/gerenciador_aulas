import static org.junit.jupiter.api.Assertions.*;

import br.edu.ifpb.es.daw.DawException;
import br.edu.ifpb.es.daw.dao.ProfessorDAO;
import br.edu.ifpb.es.daw.dao.impl.ProfessorDAOImpl;
import br.edu.ifpb.es.daw.entities.Professor;
import org.junit.jupiter.api.*;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class MainProfessorSaveTest {

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
    public void deveSalvarProfessorComSucesso() throws DawException {
        ProfessorDAO dao = new ProfessorDAOImpl(emf);

        Professor professor = new Professor();
        professor.setNome("João da Silva");
        professor.setEmail(System.nanoTime() + "joao.silva@exemplo.com");
        professor.setSenha("senhaSegura123");

        dao.save(professor);

        assertNotNull(professor.getId(), "O ID do professor não pode ser nulo após salvar.");
    }
}
