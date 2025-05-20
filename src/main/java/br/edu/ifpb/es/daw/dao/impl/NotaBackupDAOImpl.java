package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.NotaBackupDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.entities.NotaBackup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaBackupDAOImpl extends AbstractDAOImpl<NotaBackup, Long> implements NotaBackupDAO {

    public NotaBackupDAOImpl() {
        super("nota");  // Nome da tabela principal
    }

    @Override
    protected String getInsertSql(NotaBackup obj) {
        // Não será utilizado neste DAO
        return null;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, NotaBackup obj) throws SQLException {
        // Não será utilizado neste DAO
    }

    @Override
    protected String getUpdateSql(NotaBackup obj) {
        // Não será utilizado neste DAO
        return null;
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, NotaBackup obj) throws SQLException {
        // Não será utilizado neste DAO
    }

    @Override
    protected NotaBackup mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String valor = rs.getString("valor");
        String alunoNome = rs.getString("aluno_nome");
        String alunoMatricula = rs.getString("aluno_matricula");
        String disciplinaNome = rs.getString("disciplina_nome");

        return new NotaBackup(id, valor, alunoNome, alunoMatricula, disciplinaNome);
    }

    // Método específico para recuperar notas completas com dados de aluno e disciplina
    @Override
    public List<NotaBackup> getAll() throws PersistenciaDawException {
        String sql = "SELECT nota.id, nota.valor, aluno.nome AS aluno_nome, aluno.matricula AS aluno_matricula, " +
                "disciplina.nome AS disciplina_nome " +
                "FROM nota " +
                "JOIN aluno ON nota.aluno_id = aluno.id " +
                "JOIN disciplina ON nota.disciplina_id = disciplina.id";

        List<NotaBackup> notas = new ArrayList<>();
        //colocar toda a conexão em um objeto separado!!!
        try (PreparedStatement ps = getConnectionInstance().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                notas.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao recuperar todas as notas completas.", e);
        }
        return notas;
    }
}