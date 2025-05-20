package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.NotaDAO;
import br.edu.ifpb.es.daw.entities.Nota;

import java.sql.*;

public class NotaDAOImpl extends AbstractDAOImpl<Nota, Long> implements NotaDAO {

    public NotaDAOImpl() {
        super("nota");
    }

    @Override
    protected String getInsertSql(Nota nota) {
        return "INSERT INTO nota (descricao, valor, aluno_id, disciplina_id) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Nota nota) throws SQLException {
        ps.setString(1, nota.getDescricao());
        ps.setDouble(2, nota.getValor());
        ps.setInt(3,nota.getId_aluno());
        ps.setInt(4,nota.getId_disciplina());
    }

    @Override
    protected String getUpdateSql(Nota nota) {
        return "UPDATE nota SET descricao = ?, valor = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Nota nota) throws SQLException {
        ps.setString(1, nota.getDescricao());
        ps.setDouble(2, nota.getValor());
        ps.setLong(3, nota.getId());
    }

    @Override
    protected Nota mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String descricao = rs.getString("descricao");
        Double valor = rs.getDouble("valor");
        Integer aluno_id = rs.getInt("aluno_id");
        Integer disciplina_id = rs.getInt("disciplina_id");

        Nota nota = new Nota();
        nota.setId(id);
        nota.setDescricao(descricao);
        nota.setValor(valor);
        nota.setId_aluno(aluno_id);
        nota.setId_disciplina(disciplina_id);

        return nota;
    }
}
