package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.dao.impl.AlunosDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.AulaDAOImpl;
import br.edu.ifpb.es.daw.dao.ProfessorDAO;
import br.edu.ifpb.es.daw.dao.impl.ProfessorDAOImpl;
import br.edu.ifpb.es.daw.entities.Aluno;
import br.edu.ifpb.es.daw.entities.Aula;
import br.edu.ifpb.es.daw.entities.Professor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MainAulaSave {
    public static void main(String[] args) throws DawException {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                Professor professor = em.find(Professor.class, 1L);
                Aluno aluno1 = em.find(Aluno.class, 1L);
                Aluno aluno2 = em.find(Aluno.class, 2L);

                if (professor == null || aluno1 == null || aluno2 == null) {
                    System.err.println("Entidades não encontradas!");
                    return;
                }

                Aula aula = new Aula();
                aula.setData("03/05/2025");
                aula.setConteudo("Prática no laboratório");
                aula.setQuantidadeFalta(10);
                aula.setProfessor(professor);

                em.persist(aula);

                aluno1.getAulas().add(aula);
                aluno2.getAulas().add(aula);

                aula.getAlunos().add(aluno1);
                aula.getAlunos().add(aluno2);

                em.merge(aluno1);
                em.merge(aluno2);

                tx.commit();

                System.out.println("Aula salva com sucesso!");

            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw e;
            } finally {
                em.close();
            }
        } catch (Exception e) {
            System.err.println("Erro ao salvar aula: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
