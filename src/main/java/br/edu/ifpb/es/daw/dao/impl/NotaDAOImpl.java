package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.NotaDAO;
import br.edu.ifpb.es.daw.entities.Nota;

import java.sql.*;

public class NotaDAOImpl extends AbstractDAOImpl<Nota, Long> implements NotaDAO {

    public NotaDAOImpl() {
        super("nota"); // "nota" é o nome da tabela no banco de dados
    }

    // Implementa o SQL de INSERT
    @Override
    protected String getInsertSql(Nota nota) {
        return "INSERT INTO nota (descricao, valor, aluno_id, disciplina_id) VALUES (?, ?, ?, ?)";
    }

    // Define os parâmetros para o INSERT
    @Override
    protected void setInsertParameters(PreparedStatement ps, Nota nota) throws SQLException {
        ps.setString(1, nota.getDescricao());
        ps.setDouble(2, nota.getValor());
        ps.setInt(3,nota.getId_aluno());
        ps.setInt(4,nota.getId_disciplina());
    }

    // Implementa o SQL de UPDATE
    @Override
    protected String getUpdateSql(Nota nota) {
        return "UPDATE nota SET descricao = ?, valor = ? WHERE id = ?";
    }

    // Define os parâmetros para o UPDATE
    @Override
    protected void setUpdateParameters(PreparedStatement ps, Nota nota) throws SQLException {
        ps.setString(1, nota.getDescricao());
        ps.setDouble(2, nota.getValor());
        ps.setLong(3, nota.getId());
    }

    // Mapeia o ResultSet para a entidade Nota
    @Override
    protected Nota mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String descricao = rs.getString("descricao");
        Double valor = rs.getDouble("valor");
        Integer aluno_id = rs.getInt("aluno_id");
        Integer disciplina_id = rs.getInt("disciplina_id");

        // Criando uma nova instância de Nota e definindo os valores através dos setters
        Nota nota = new Nota();
        nota.setId(id);
        nota.setDescricao(descricao);
        nota.setValor(valor);
        nota.setId_aluno(aluno_id);
        nota.setId_disciplina(disciplina_id);

        return nota;
    }
}
