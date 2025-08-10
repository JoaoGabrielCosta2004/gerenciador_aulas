package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.Config;
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
    public List<Turma> findAll() {
        List<Turma> turmas = new ArrayList<>();
        String sql = "SELECT id, nome FROM turma";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Turma turma = new Turma();
                turma.setId(rs.getLong("id"));
                turma.setNome(rs.getString("nome"));
                turmas.add(turma);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return turmas;
    }

    @Override
    protected Turma mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String nome = rs.getString("nome");
        Integer ano = rs.getInt("ano");
        return new Turma(id, nome, ano);
    }


}
