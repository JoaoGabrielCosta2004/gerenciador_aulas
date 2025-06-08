package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.entities.Professor;
import br.edu.ifpb.es.daw.entities.Turma;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;



public class MainTurmaSave {
    public static void main(String[] args) throws DawException {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                Professor professor = em.find(Professor.class, 1L);

                if (professor == null) {
                    System.err.println("Professor com ID 1 não encontrado!");
                    return;
                }


                Turma turma = new Turma();
                turma.setNome("5° ano B 2025");
                turma.setAno(5);

                em.persist(turma);
                em.flush();

                professor.getTurmas().add(turma);

                turma.getProfessores().add(professor);

                em.merge(professor);

                tx.commit();

                System.out.println("Turma salva com sucesso!");
                System.out.println("Turma: " + turma);
                System.out.println("Professor associado: " + professor.getNome());

            } catch (Exception e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                System.err.println("Erro ao salvar turma: " + e.getMessage());
                e.printStackTrace();
            } finally {
                em.close();
            }
        }
    }
}

