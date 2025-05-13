package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.ProfessorDAO;
import br.edu.ifpb.es.daw.entities.Professor;

import java.sql.*;

public class ProfessorDAOImpl extends AbstractDAOImpl<Professor, Long> implements ProfessorDAO {

    public ProfessorDAOImpl(Connection connection) {
        super(connection, "professor"); // "professor" é o nome da tabela no banco de dados
    }

    // Implementa o SQL de INSERT
    @Override
    protected String getInsertSql(Professor professor) {
        return "INSERT INTO professor (nome, email, senha) VALUES (?, ?, ?)";
    }

    // Define os parâmetros para o INSERT
    @Override
    protected void setInsertParameters(PreparedStatement ps, Professor professor) throws SQLException {
        ps.setString(1, professor.getNome());
        ps.setString(2, professor.getEmail());
        ps.setString(3, professor.getSenha());
    }

    // Implementa o SQL de UPDATE
    @Override
    protected String getUpdateSql(Professor professor) {
        return "UPDATE professor SET nome = ?, email = ?, senha = ? WHERE id = ?";
    }

    // Define os parâmetros para o UPDATE
    @Override
    protected void setUpdateParameters(PreparedStatement ps, Professor professor) throws SQLException {
        ps.setString(1, professor.getNome());
        ps.setString(2, professor.getEmail());
        ps.setString(3, professor.getSenha());
        ps.setLong(4, professor.getId());
    }

    // Mapeia o ResultSet para a entidade Professor
    @Override
    protected Professor mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String nome = rs.getString("nome");
        String email = rs.getString("email");
        String senha = rs.getString("senha");
        return new Professor(id, nome, email, senha);
    }
}
