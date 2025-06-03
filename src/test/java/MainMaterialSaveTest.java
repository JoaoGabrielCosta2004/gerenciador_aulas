import static org.junit.jupiter.api.Assertions.*;

import br.edu.ifpb.es.daw.DawException;
import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.dao.MaterialDAO;
import br.edu.ifpb.es.daw.dao.impl.AulaDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.MaterialDAOImpl;
import br.edu.ifpb.es.daw.entities.Aula;
import br.edu.ifpb.es.daw.entities.Material;
import org.junit.jupiter.api.*;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class MainMaterialSaveTest {

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
    public void deveSalvarMaterialComSucesso() throws DawException {
        MaterialDAO materialdao = new MaterialDAOImpl(emf);
        AulaDAO aulaDAO = new AulaDAOImpl(emf);

        Aula aula = aulaDAO.getByID(1L);
        assertNotNull(aula, "Aula não pode ser nula");

        Material material = new Material();
        material.setTitulo("Prova matematica 5° ano");
        material.setTipo("Prova");
        material.setLink(System.nanoTime());
        material.setAula(aula);

        materialdao.save(material);

        assertNotNull(material.getId(), "O ID do material não pode ser nulo após salvar.");
    }
}