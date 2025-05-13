package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.entities.Aluno;

import java.sql.*;
import java.time.LocalDate;

public class AlunosDAOImpl extends AbstractDAOImpl<Aluno, Long> implements AlunoDAO {

    public AlunosDAOImpl() {
        super("aluno"); // "aluno" é o nome da tabela no banco de dados
    }

    // Implementa o SQL de INSERT
    @Override
    protected String getInsertSql(Aluno aluno) {
        return "INSERT INTO aluno (nome, matricula, data_nascimento) VALUES (?, ?, ?)";
    }

    // Define os parâmetros para o INSERT
    @Override
    protected void setInsertParameters(PreparedStatement ps, Aluno aluno) throws SQLException {
        ps.setString(1, aluno.getNome());
        ps.setString(2, aluno.getMatricula());
        ps.setDate(3, Date.valueOf(aluno.getDataNascimento()));
    }

    // Implementa o SQL de UPDATE
    @Override
    protected String getUpdateSql(Aluno aluno) {
        return "UPDATE aluno SET nome = ?, matricula = ?, data_nascimento = ? WHERE id = ?";
    }

    // Define os parâmetros para o UPDATE
    @Override
    protected void setUpdateParameters(PreparedStatement ps, Aluno aluno) throws SQLException {
        ps.setString(1, aluno.getNome());
        ps.setString(2, aluno.getMatricula());
        ps.setDate(3, Date.valueOf(aluno.getDataNascimento()));
        ps.setLong(4, aluno.getId());
    }

    // Mapeia o ResultSet para a entidade Aluno
    @Override
    protected Aluno mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String nome = rs.getString("nome");
        String matricula = rs.getString("matricula");
        LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
        return new Aluno(id, nome, matricula, dataNascimento);
    }
}
