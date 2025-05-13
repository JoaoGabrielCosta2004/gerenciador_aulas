package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.DisciplinaDAO;
import br.edu.ifpb.es.daw.entities.Disciplina;

import java.sql.*;

public class DisciplinaDAOImpl extends AbstractDAOImpl<Disciplina, Long> implements DisciplinaDAO {

    public DisciplinaDAOImpl(Connection connection) {
        super(connection, "disciplina"); // "disciplina" é o nome da tabela no banco de dados
    }

    // Implementa o SQL de INSERT
    @Override
    protected String getInsertSql(Disciplina disciplina) {
        return "INSERT INTO disciplina (nome) VALUES (?)";
    }

    // Define os parâmetros para o INSERT
    @Override
    protected void setInsertParameters(PreparedStatement ps, Disciplina disciplina) throws SQLException {
        ps.setString(1, disciplina.getNome());
    }

    // Implementa o SQL de UPDATE
    @Override
    protected String getUpdateSql(Disciplina disciplina) {
        return "UPDATE disciplina SET nome = ? WHERE id = ?";
    }

    // Define os parâmetros para o UPDATE
    @Override
    protected void setUpdateParameters(PreparedStatement ps, Disciplina disciplina) throws SQLException {
        ps.setString(1, disciplina.getNome());
        ps.setLong(2, disciplina.getId());
    }

    // Mapeia o ResultSet para a entidade Disciplina
    @Override
    protected Disciplina mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String nome = rs.getString("nome");

        // Criando uma nova instância de Disciplina e definindo os valores através dos setters
        Disciplina disciplina = new Disciplina();  // Usando o construtor padrão
        disciplina.setId(id);
        disciplina.setNome(nome);

        return disciplina;
    }
}
