package br.edu.ifpb.es.daw.dao;

import br.edu.ifpb.es.daw.entities.ProfessorTurma;

import java.util.List;

public interface ProfessorTurmaDAO {

    void save(ProfessorTurma pt) throws PersistenciaDawException;

    void delete(Long professorId, Long turmaId) throws PersistenciaDawException;

    List<ProfessorTurma> findByProfessorId(Long professorId) throws PersistenciaDawException;

    List<ProfessorTurma> findByTurmaId(Long turmaId) throws PersistenciaDawException;

    boolean existsAssociation(Long professorId, Long turmaId) throws PersistenciaDawException;
}