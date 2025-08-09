package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.Config;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.ProfessorTurmaDAO;
import br.edu.ifpb.es.daw.entities.ProfessorTurma;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProfessorTurmaDAOImpl implements ProfessorTurmaDAO {

    private Connection connection;

    public ProfessorTurmaDAOImpl() {
        try {
            Properties properties = Config.loadConfig();
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.usuario");
            String password = properties.getProperty("db.senha");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar no banco", e);
        }
    }

    @Override
    public void save(ProfessorTurma pt) throws PersistenciaDawException {
        String sql = "INSERT INTO professor_turma (professor_id, turma_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, pt.getProfessorId());
            ps.setLong(2, pt.getTurmaId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao salvar associação professor-turma", e);
        }
    }

    @Override
    public void delete(Long professorId, Long turmaId) throws PersistenciaDawException {
        String sql = "DELETE FROM professor_turma WHERE professor_id = ? AND turma_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, professorId);
            ps.setLong(2, turmaId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao deletar associação professor-turma", e);
        }
    }

    @Override
    public List<ProfessorTurma> findByProfessorId(Long professorId) throws PersistenciaDawException {
        String sql = "SELECT * FROM professor_turma WHERE professor_id = ?";
        List<ProfessorTurma> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, professorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ProfessorTurma(
                            rs.getLong("professor_id"),
                            rs.getLong("turma_id")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar associações por professor", e);
        }
        return list;
    }

    @Override
    public List<ProfessorTurma> findByTurmaId(Long turmaId) throws PersistenciaDawException {
        String sql = "SELECT * FROM professor_turma WHERE turma_id = ?";
        List<ProfessorTurma> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, turmaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ProfessorTurma(
                            rs.getLong("professor_id"),
                            rs.getLong("turma_id")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar associações por turma", e);
        }
        return list;
    }

    @Override
    public boolean existsAssociation(Long professorId, Long turmaId) throws PersistenciaDawException {
        String sql = "SELECT COUNT(*) FROM professor_turma WHERE professor_id = ? AND turma_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, professorId);
            ps.setLong(2, turmaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao verificar associação", e);
        }
        return false;
    }
}
