package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.MaterialDAO;
import br.edu.ifpb.es.daw.dao.NotaDAO;
import br.edu.ifpb.es.daw.dao.impl.MaterialDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.NotaDAOImpl;
import br.edu.ifpb.es.daw.dao.DisciplinaDAO;
import br.edu.ifpb.es.daw.dao.impl.DisciplinaDAOImpl;
import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.dao.impl.AlunosDAOImpl;
import br.edu.ifpb.es.daw.entities.Material;
import br.edu.ifpb.es.daw.entities.Nota;
import br.edu.ifpb.es.daw.entities.Aluno;
import br.edu.ifpb.es.daw.entities.Disciplina;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainNotaSave {
    public static void main(String[] args) throws DawException {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            NotaDAO notaDao = new NotaDAOImpl(emf);
            AlunoDAO alunoDao = new AlunosDAOImpl(emf);
            DisciplinaDAO disciplinaDao = new DisciplinaDAOImpl(emf);
            MaterialDAO materialdao = new MaterialDAOImpl(emf);

            Aluno aluno = alunoDao.getByID(1L);
            Disciplina disciplina = disciplinaDao.getByID(1L);
            Material material = materialdao.getByID(1L);

            Nota nota = new Nota();
            nota.setValor(10.0);
            nota.setDescricao("Prova de matemática aplicada dia 03/05/2025");

            nota.setAluno(aluno);
            nota.setDisciplina(disciplina);
            nota.setMaterial(material);

            notaDao.save(nota);

            System.out.println("Nota salva com sucesso!");
        }
    }
}
