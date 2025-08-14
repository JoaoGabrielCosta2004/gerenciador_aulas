package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.entities.Aula;
import br.edu.ifpb.es.daw.app.service.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AulaDAOImpl implements AulaDAO {

    public AulaDAOImpl() {
        // Construtor vazio, usamos Conexao.getConexao() nos métodos
    }

    /**
     * Insere a aula no banco e retorna o ID gerado
     */
    @Override
    public Long saveAndReturnId(Aula aula) throws PersistenciaDawException {
        String sql = "INSERT INTO aula (data, conteudo, turma_id, professor_id, quantidadefalta) VALUES (?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(aula.getData())); // LocalDate -> SQL Date
            ps.setString(2, aula.getConteudo());
            ps.setLong(3, aula.getId_turma());
            ps.setLong(4, aula.getIdProfessor());
            ps.setInt(5,aula.getQuantidadeFalta());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Long idGerado = rs.getLong("id");
                    aula.setId(idGerado);
                    return idGerado;
                }
            }

            throw new PersistenciaDawException("Falha ao inserir aula: ID não retornado.");
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao salvar aula", e);
        }
    }

    /**
     * Implementa o método da interface AulaDAO
     */
    @Override
    public void inserir(Aula aula) throws PersistenciaDawException {
        saveAndReturnId(aula);
    }

    /**
     * Atualiza uma aula existente
     */
    public void atualizar(Aula aula) throws PersistenciaDawException {
        String sql = "UPDATE aula SET data = ?, conteudo = ?, turma_id = ? WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(aula.getData()));
            ps.setString(2, aula.getConteudo());
            ps.setLong(3, aula.getId_turma());
            ps.setLong(4, aula.getId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new PersistenciaDawException("Nenhuma aula encontrada para atualizar com ID: " + aula.getId());
            }

        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao atualizar aula", e);
        }
    }

    /**
     * Busca uma aula pelo ID
     */
    public Aula buscarPorId(Long id) throws PersistenciaDawException {
        String sql = "SELECT * FROM aula WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Aula aula = new Aula();
                    aula.setId(rs.getLong("id"));
                    aula.setData(rs.getDate("data").toLocalDate());
                    aula.setConteudo(rs.getString("conteudo"));
                    aula.setId_turma(rs.getLong("turma_id"));
                    return aula;
                }
            }

            return null; // não encontrou
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao buscar aula por ID", e);
        }
    }

    @Override
    public void save(Aula aula) throws PersistenciaDawException {
        saveAndReturnId(aula);
    }

    @Override
    public Aula update(Aula obj) throws PersistenciaDawException {
        return null;
    }

    @Override
    public void delete(Long primaryKey) throws PersistenciaDawException {

    }

    @Override
    public Aula getByID(Long primaryKey) throws PersistenciaDawException {
        return null;
    }

    @Override
    public List<Aula> getAll() throws PersistenciaDawException {
        return List.of();
    }

    @Override
    public void restartSequence() throws PersistenciaDawException {

    }

    @Override
    public List<Aula> buscarPorProfessor(Long idProfessor) throws PersistenciaDawException {
        List<Aula> aulas = new ArrayList<>();
        String sql = "SELECT * FROM aula WHERE professor_id = ? ORDER BY data";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setLong(1, idProfessor);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Aula aula = new Aula();
                    aula.setId(rs.getLong("id"));
                    aula.setQuantidadeFalta(rs.getInt("quantidadefalta"));
                    aula.setId_turma(rs.getLong("turma_id"));
                    aula.setConteudo(rs.getString("conteudo"));
                    aula.setData(rs.getDate("data").toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    aulas.add(aula);
                }
            }
        } catch (Exception e) {
            throw new PersistenciaDawException("Erro ao buscar aulas do professor", e);
        }

        return aulas;
    }
}
