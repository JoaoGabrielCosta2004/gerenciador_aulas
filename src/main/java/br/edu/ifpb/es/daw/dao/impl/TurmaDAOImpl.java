package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.TurmaDAO;
import br.edu.ifpb.es.daw.entities.Turma;

import java.sql.*;

public class TurmaDAOImpl extends AbstractDAOImpl<Turma, Long> implements TurmaDAO {

    public TurmaDAOImpl(Connection connection) {
        super(connection, "turma"); // "turma" é o nome da tabela no banco de dados
    }

    // Implementa o SQL de INSERT
    @Override
    protected String getInsertSql(Turma turma) {
        return "INSERT INTO turma (nome, ano) VALUES (?, ?)";
    }

    // Define os parâmetros para o INSERT
    @Override
    protected void setInsertParameters(PreparedStatement ps, Turma turma) throws SQLException {
        ps.setString(1, turma.getNome());
        ps.setInt(2, turma.getAno());
    }

    // Implementa o SQL de UPDATE
    @Override
    protected String getUpdateSql(Turma turma) {
        return "UPDATE turma SET nome = ?, ano = ? WHERE id = ?";
    }

    // Define os parâmetros para o UPDATE
    @Override
    protected void setUpdateParameters(PreparedStatement ps, Turma turma) throws SQLException {
        ps.setString(1, turma.getNome());
        ps.setInt(2, turma.getAno());
        ps.setLong(3, turma.getId());
    }

    // Mapeia o ResultSet para a entidade Turma
    @Override
    protected Turma mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String nome = rs.getString("nome");
        Integer ano = rs.getInt("ano");
        return new Turma(id, nome, ano);
    }
}
