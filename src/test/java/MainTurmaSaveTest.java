import br.edu.ifpb.es.daw.DawException;
import br.edu.ifpb.es.daw.dao.TurmaDAO;
import br.edu.ifpb.es.daw.dao.impl.TurmaDAOImpl;
import br.edu.ifpb.es.daw.entities.Turma;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

public class MainTurmaSaveTest {

    private static EntityManagerFactory emf;
    private TurmaDAO dao;

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
        dao = new TurmaDAOImpl(emf);
    }

    @Test
    public void deveSalvarTurmaComSucesso() throws DawException {
        Turma turma = new Turma();
        turma.setNome(System.nanoTime() + "5° ano TESTE");
        turma.setAno(5);

        dao.save(turma);

        assertNotNull(turma.getId(), "O ID da turma não pode ser nulo após salvar.");
    }
}