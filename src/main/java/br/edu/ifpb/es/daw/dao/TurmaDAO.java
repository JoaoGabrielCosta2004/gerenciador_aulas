package br.edu.ifpb.es.daw.dao;

import br.edu.ifpb.es.daw.entities.Turma;

import java.util.List;

public interface TurmaDAO extends DAO<Turma, Long> {
    List<Turma> findAll();
}
