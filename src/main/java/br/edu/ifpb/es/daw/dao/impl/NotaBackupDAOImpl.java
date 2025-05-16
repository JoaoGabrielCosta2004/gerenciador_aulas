package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.NotaBackupDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.entities.NotaBackup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaBackupDAOImpl extends AbstractDAOImpl<NotaBackup, Long> implements NotaBackupDAO{
    public NotaBackupDAOImpl() {
        super("nota");  // Passa o nome da tabela para o construtor da classe abstrata
    }

    @Override
    protected String getInsertSql(NotaBackup obj) {
        // Esse método provavelmente não será usado, pois você só quer ler os dados
        return null;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, NotaBackup obj) throws SQLException {
        // Esse método provavelmente não será usado
    }

    @Override
    protected String getUpdateSql(NotaBackup obj) {
        // Esse método provavelmente não será usado, pois você só quer ler os dados
        return null;
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, NotaBackup obj) throws SQLException {
        // Esse método provavelmente não será usado
    }

    @Override
    protected NotaBackup mapResultSetToEntity(ResultSet rs) throws SQLException {
        // Agora usando Long para o ID
        Long id = rs.getLong("nota.id");  // Usando getLong para o ID
        String valor = rs.getString("nota.valor");
        String alunoNome = rs.getString("aluno.nome");
        String alunoMatricula = rs.getString("aluno.matricula");
        String disciplinaNome = rs.getString("disciplina.nome");

        // Retorna uma instância de `NotaBackup` com todos os dados
        return new NotaBackup(id, valor,alunoNome, alunoMatricula, disciplinaNome);
    }

    // Método para recuperar todas as notas com dados completos de aluno e disciplina
    public List<NotaBackup> getAllNotasCompletas() throws PersistenciaDawException {
        String sql = "SELECT nota.id, nota.valor, nota.descricao, aluno.nome AS aluno_nome, aluno.matricula AS aluno_matricula, " +
                "disciplina.nome AS disciplina_nome " +
                "FROM nota " +
                "JOIN aluno ON nota.aluno_id = aluno.id " +
                "JOIN disciplina ON nota.disciplina_id = disciplina.id";

        List<NotaBackup> notas = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                notas.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            throw new PersistenciaDawException("Erro ao recuperar todas as notas.", e);
        }
        return notas;
    }

}
