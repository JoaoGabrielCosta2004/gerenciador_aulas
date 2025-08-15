package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.app.service.Conexao;
import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.entities.Aluno;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunosDAOImpl extends AbstractDAOImpl<Aluno, Long> implements AlunoDAO {

    public AlunosDAOImpl() {
        super("aluno"); // "aluno" é o nome da tabela no banco de dados
    }

    @Override
    protected String getInsertSql(Aluno aluno) {
        return "INSERT INTO aluno (nome, matricula, data_nascimento, turma_id) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Aluno aluno) throws SQLException {
        ps.setString(1, aluno.getNome());
        ps.setString(2, aluno.getMatricula());
        ps.setDate(3, Date.valueOf(aluno.getDataNascimento()));

        if (aluno.getId_turma() != null) {
            ps.setLong(4, aluno.getId_turma());
        } else {
            ps.setNull(4, Types.BIGINT);
        }
    }

    @Override
    protected String getUpdateSql(Aluno aluno) {
        return "UPDATE aluno SET nome = ?, matricula = ?, data_nascimento = ?, turma_id = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Aluno aluno) throws SQLException {
        ps.setString(1, aluno.getNome());
        ps.setString(2, aluno.getMatricula());
        ps.setDate(3, Date.valueOf(aluno.getDataNascimento()));

        if (aluno.getId_turma() != null ) {
            ps.setLong(4, aluno.getId_turma());
        } else {
            ps.setNull(4, Types.BIGINT);
        }

        ps.setLong(5, aluno.getId());
    }

    @Override
    protected Aluno mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String nome = rs.getString("nome");
        String matricula = rs.getString("matricula");
        LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
        Long turmaId = rs.getObject("turma_id", Long.class);
        return new Aluno(id, nome, matricula, dataNascimento, turmaId);
    }

    @Override
    public List<Aluno> buscarPorTurma(Long idTurma) throws PersistenciaDawException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno WHERE turma_id = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setLong(1, idTurma);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setId(rs.getLong("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setMatricula(rs.getString("matricula"));
                    aluno.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                    aluno.setId_turma(rs.getLong("turma_id"));
                    alunos.add(aluno);
                }
            }
        } catch (Exception e) {
            throw new PersistenciaDawException("Erro ao buscar alunos da turma", e);
        }

        return alunos;
    }

}
