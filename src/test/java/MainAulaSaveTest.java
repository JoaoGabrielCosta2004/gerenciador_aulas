import static org.junit.jupiter.api.Assertions.*;

import br.edu.ifpb.es.daw.DawException;
import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.dao.ProfessorDAO;
import br.edu.ifpb.es.daw.dao.impl.AulaDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.ProfessorDAOImpl;
import br.edu.ifpb.es.daw.entities.Aula;
import br.edu.ifpb.es.daw.entities.Professor;
import org.junit.jupiter.api.*;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class MainAulaSaveTest {

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
    public void deveSalvarAulaComSucesso() throws DawException {
        AulaDAO aulaDAO = new AulaDAOImpl(emf);
        ProfessorDAO professorDAO = new ProfessorDAOImpl(emf);

        Professor professor = professorDAO.getByID(1L);
        assertNotNull(professor, "Professor não pode ser nulo");

        Aula aula = new Aula();
        aula.setData("03/05/2025");
        aula.setConteudo(System.nanoTime()+"pratica no lab");
        aula.setQuantidadeFalta(10);
        aula.setProfessor(professor);

        aulaDAO.save(aula);

        assertNotNull(aula.getId(), "O ID da aula não pode ser nulo após salvar.");
    }
}
