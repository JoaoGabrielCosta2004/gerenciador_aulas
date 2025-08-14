package br.edu.ifpb.es.daw.dao;

import br.edu.ifpb.es.daw.entities.AlunoAula;

import java.util.List;

public interface AlunoAulaDAO {
    void save(AlunoAula aa) throws PersistenciaDawException;

    void delete(Long alunoId, Long aulaId) throws PersistenciaDawException;

    List<AlunoAula> findByAlunoId(Long alunoId) throws PersistenciaDawException;

    List<AlunoAula> findByAulaId(Long aulaId) throws PersistenciaDawException;

    boolean existsAssociation(Long alunoId, Long aulaId) throws PersistenciaDawException;

    void inserir(Long alunoId, Long aulaId, int faltas) throws PersistenciaDawException;
}
