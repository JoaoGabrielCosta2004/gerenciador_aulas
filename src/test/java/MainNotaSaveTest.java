import static org.junit.jupiter.api.Assertions.*;

import br.edu.ifpb.es.daw.DawException;
import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.dao.DisciplinaDAO;
import br.edu.ifpb.es.daw.dao.MaterialDAO;
import br.edu.ifpb.es.daw.dao.NotaDAO;
import br.edu.ifpb.es.daw.dao.impl.AlunosDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.DisciplinaDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.MaterialDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.NotaDAOImpl;
import br.edu.ifpb.es.daw.entities.Aluno;
import br.edu.ifpb.es.daw.entities.Disciplina;
import br.edu.ifpb.es.daw.entities.Material;
import br.edu.ifpb.es.daw.entities.Nota;
import org.junit.jupiter.api.*;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class MainNotaSaveTest {

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
    public void deveSalvarNotaComSucesso() throws DawException {
        NotaDAO notaDao = new NotaDAOImpl(emf);
        AlunoDAO alunoDao = new AlunosDAOImpl(emf);
        DisciplinaDAO disciplinaDao = new DisciplinaDAOImpl(emf);
        MaterialDAO materialdao = new MaterialDAOImpl(emf);

        Aluno aluno = alunoDao.getByID(1L);
        Disciplina disciplina = disciplinaDao.getByID(1L);
        Material material = materialdao.getByID(1L);

        assertNotNull(aluno, "Aluno não pode ser nulo");
        assertNotNull(disciplina, "Disciplina não pode ser nula");
        assertNotNull(material, "Material não pode ser nulo");

        Nota nota = new Nota();
        nota.setValor(10.0);
        nota.setDescricao(System.nanoTime()"Prova de matemática aplicada dia 03/05/2025");
        nota.setAluno(aluno);
        nota.setDisciplina(disciplina);
        nota.setMaterial(material);

        notaDao.save(nota);

        assertNotNull(nota.getId(), "O ID da nota não pode ser nulo após salvar.");
    }
}
