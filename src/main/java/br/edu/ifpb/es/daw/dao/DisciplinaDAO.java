package br.edu.ifpb.es.daw.dao;

import br.edu.ifpb.es.daw.entities.Disciplina;

import java.util.List;

public interface DisciplinaDAO extends DAO<Disciplina, Long> {
    public void linkProfessorDisciplina(Long professorId, Long disciplinaId) throws PersistenciaDawException;
}
