package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.TurmaDAO;
import br.edu.ifpb.es.daw.entities.Turma;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAOImpl extends AbstractDAOImpl<Turma, Long> implements TurmaDAO {

    public TurmaDAOImpl() {
        super("turma");
    }

    @Override
    protected String getInsertSql(Turma turma) {
        return "INSERT INTO turma (nome, ano) VALUES (?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Turma turma) throws SQLException {
        ps.setString(1, turma.getNome());
        ps.setInt(2, turma.getAno());
    }

    @Override
    protected String getUpdateSql(Turma turma) {
        return "UPDATE turma SET nome = ?, ano = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Turma turma) throws SQLException {
        ps.setString(1, turma.getNome());
        ps.setInt(2, turma.getAno());
        ps.setLong(3, turma.getId());
    }

    @Override
    public Turma findById(Long id) throws PersistenciaDawException {
        String sql = "SELECT * FROM turma WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Turma t = new Turma();
                    t.setId(rs.getLong("id"));
                    t.setNome(rs.getString("nome"));
                    return t;
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar turma por ID", e);
        }
        return null;
    }

    @Override
    protected Turma mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String nome = rs.getString("nome");
        Integer ano = rs.getInt("ano");
        return new Turma(id, nome, ano);
    }


}
