package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.DisciplinaDAO;
import br.edu.ifpb.es.daw.entities.Disciplina;

import java.sql.*;

public class DisciplinaDAOImpl extends AbstractDAOImpl<Disciplina, Long> implements DisciplinaDAO {

    public DisciplinaDAOImpl() {
        super("disciplina");
    }

    @Override
    protected String getInsertSql(Disciplina disciplina) {
        return "INSERT INTO disciplina (nome) VALUES (?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Disciplina disciplina) throws SQLException {
        ps.setString(1, disciplina.getNome());
    }

    @Override
    protected String getUpdateSql(Disciplina disciplina) {
        return "UPDATE disciplina SET nome = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Disciplina disciplina) throws SQLException {
        ps.setString(1, disciplina.getNome());
        ps.setLong(2, disciplina.getId());
    }

    @Override
    protected Disciplina mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String nome = rs.getString("nome");

        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setNome(nome);

        return disciplina;
    }
}
