package br.edu.ifpb.es.daw.dao;

import br.edu.ifpb.es.daw.entities.Aluno;

import java.util.List;

public interface AlunoDAO extends DAO<Aluno, Long> {
    List<Aluno> buscarPorTurma(Long idTurma) throws PersistenciaDawException;
}