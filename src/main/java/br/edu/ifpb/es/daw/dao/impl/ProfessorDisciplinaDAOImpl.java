package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.ProfessorDisciplinaDAO;
import br.edu.ifpb.es.daw.entities.ProfessorDisciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDisciplinaDAOImpl extends AbstractDAOImpl<ProfessorDisciplina, Long> implements ProfessorDisciplinaDAO {

    public ProfessorDisciplinaDAOImpl() {
        super("professor_disciplina");
    }

    @Override
    protected String getInsertSql(ProfessorDisciplina pd) {
        return "INSERT INTO professor_disciplina (professor_id, disciplina_id) VALUES (?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, ProfessorDisciplina pd) throws SQLException {
        ps.setLong(1, pd.getProfessorId());
        ps.setLong(2, pd.getDisciplinaId());
    }

    @Override
    protected String getUpdateSql(ProfessorDisciplina pd) {
        return "UPDATE professor_disciplina SET professor_id = ?, disciplina_id = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, ProfessorDisciplina pd) throws SQLException {
        ps.setLong(1, pd.getProfessorId());
        ps.setLong(2, pd.getDisciplinaId());
    }

    @Override
    protected ProfessorDisciplina mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        Long professorId = rs.getLong("professor_id");
        Long disciplinaId = rs.getLong("disciplina_id");
        return new ProfessorDisciplina(id, professorId, disciplinaId);
    }

    @Override
    public List<ProfessorDisciplina> findByProfessorId(Long professorId) throws PersistenciaDawException {
        List<ProfessorDisciplina> list = new ArrayList<>();
        String sql = "SELECT * FROM professor_disciplina WHERE professor_id = ?";
        try (PreparedStatement ps = getConnectionInstance().prepareStatement(sql)) {
            ps.setLong(1, professorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar por professorId: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<ProfessorDisciplina> findByDisciplinaId(Long disciplinaId) throws PersistenciaDawException {
        List<ProfessorDisciplina> list = new ArrayList<>();
        String sql = "SELECT * FROM professor_disciplina WHERE disciplina_id = ?";
        try (PreparedStatement ps = getConnectionInstance().prepareStatement(sql)) {
            ps.setLong(1, disciplinaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar por disciplinaId: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public boolean existsAssociation(Long professorId, Long disciplinaId) throws PersistenciaDawException {
        String sql = "SELECT COUNT(*) FROM professor_disciplina WHERE professor_id = ? AND disciplina_id = ?";
        try (PreparedStatement ps = getConnectionInstance().prepareStatement(sql)) {
            ps.setLong(1, professorId);
            ps.setLong(2, disciplinaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao verificar associação: " + e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void delete(Long professorId, Long disciplinaId) throws PersistenciaDawException {
        String sql = "DELETE FROM professor_disciplina WHERE professor_id = ? AND disciplina_id = ?";
        try (PreparedStatement ps = getConnectionInstance().prepareStatement(sql)) {
            ps.setLong(1, professorId);
            ps.setLong(2, disciplinaId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao deletar associação: " + e.getMessage(), e);
        }
    }
}

