package br.edu.ifpb.es.daw.dao;

import br.edu.ifpb.es.daw.entities.Aula;

import java.sql.*;
import java.util.List;

public interface AulaDAO extends DAO<Aula, Long> {
    Long saveAndReturnId(Aula aula) throws PersistenciaDawException;
    void inserir(Aula aula) throws PersistenciaDawException;
    List<Aula> buscarPorProfessor(Long idProfessor) throws PersistenciaDawException;
}
