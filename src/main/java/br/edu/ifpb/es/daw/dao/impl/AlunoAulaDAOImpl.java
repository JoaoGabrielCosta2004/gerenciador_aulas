package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.app.service.Conexao;
import br.edu.ifpb.es.daw.dao.AlunoAulaDAO;
import br.edu.ifpb.es.daw.dao.Config;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.entities.AlunoAula;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AlunoAulaDAOImpl implements AlunoAulaDAO {

    private Connection connection;

    public AlunoAulaDAOImpl() {
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
    public void save(AlunoAula aa) throws PersistenciaDawException {
        String sql = "INSERT INTO aluno_aula (aluno_id, aula_id, faltas) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, aa.getAlunoId());
            ps.setLong(2, aa.getAulaId());
            ps.setInt(3, Math.max(1, aa.getFaltas())); // garante mínimo de 1 falta
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao salvar associação aluno-aula", e);
        }
    }

    @Override
    public void delete(Long alunoId, Long aulaId) throws PersistenciaDawException {
        String sql = "DELETE FROM aluno_aula WHERE aluno_id = ? AND aula_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, alunoId);
            ps.setLong(2, aulaId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao deletar associação aluno-aula", e);
        }
    }

    @Override
    public List<AlunoAula> findByAlunoId(Long alunoId) throws PersistenciaDawException {
        String sql = "SELECT * FROM aluno_aula WHERE aluno_id = ?";
        List<AlunoAula> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, alunoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new AlunoAula(
                            rs.getLong("aluno_id"),
                            rs.getLong("aula_id"),
                            rs.getInt("faltas")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar associações por aluno", e);
        }
        return list;
    }

    @Override
    public List<AlunoAula> findByAulaId(Long aulaId) throws PersistenciaDawException {
        String sql = "SELECT * FROM aluno_aula WHERE aula_id = ?";
        List<AlunoAula> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, aulaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new AlunoAula(
                            rs.getLong("aluno_id"),
                            rs.getLong("aula_id"),
                            rs.getInt("faltas")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar associações por aula", e);
        }
        return list;
    }

    @Override
    public boolean existsAssociation(Long alunoId, Long aulaId) throws PersistenciaDawException {
        String sql = "SELECT COUNT(*) FROM aluno_aula WHERE aluno_id = ? AND aula_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, alunoId);
            ps.setLong(2, aulaId);
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

    @Override
    public void inserir(Long alunoId, Long aulaId, int faltas) throws PersistenciaDawException {
        String sql = "INSERT INTO aluno_aula (aluno_id, aula_id, faltas) VALUES (?, ?, ?)";
        try (Connection connection = Conexao.getConexao();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, alunoId);
            ps.setLong(2, aulaId);
            ps.setInt(3, faltas);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao inserir registro em aluno_aula", e);
        }
    }
}