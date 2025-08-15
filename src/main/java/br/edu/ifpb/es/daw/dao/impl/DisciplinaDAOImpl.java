package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.DisciplinaDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.entities.Disciplina;

import java.sql.*;
import java.util.List;

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

    @Override
    public List<Disciplina> getAll() throws PersistenciaDawException {
        return super.getAll();
    }

    @Override
    public void linkProfessorDisciplina(Long professorId, Long disciplinaId) throws PersistenciaDawException {
        String sql = "INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, professorId);
            ps.setLong(2, disciplinaId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao vincular professor à disciplina", e);
        }
    }
}
