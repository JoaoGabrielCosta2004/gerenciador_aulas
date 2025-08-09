package br.edu.ifpb.es.daw.dao;

import br.edu.ifpb.es.daw.entities.ProfessorDisciplina;

import java.util.List;

public interface ProfessorDisciplinaDAO {

    void save(ProfessorDisciplina pd) throws PersistenciaDawException;

    void delete(Long professorId, Long disciplinaId) throws PersistenciaDawException;

    List<ProfessorDisciplina> findByProfessorId(Long professorId) throws PersistenciaDawException;

    List<ProfessorDisciplina> findByDisciplinaId(Long disciplinaId) throws PersistenciaDawException;

    boolean existsAssociation(Long professorId, Long disciplinaId) throws PersistenciaDawException;
}
