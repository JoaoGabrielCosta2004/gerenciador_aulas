package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.entities.Aula;

import java.sql.*;
import java.time.LocalDate;

public class AulaDAOImpl extends AbstractDAOImpl<Aula, Long> implements AulaDAO {

    public AulaDAOImpl() {
        super("aula"); // "aula" é o nome da tabela no banco de dados
    }

    // Implementa o SQL de INSERT
    @Override
    protected String getInsertSql(Aula aula) {
        return "INSERT INTO aula (quantidade_falta, data, conteudo) VALUES (?, ?, ?)";
    }

    // Define os parâmetros para o INSERT
    @Override
    protected void setInsertParameters(PreparedStatement ps, Aula aula) throws SQLException {
        ps.setInt(1, aula.getQuantidadeFalta());
        ps.setDate(2, Date.valueOf(aula.getData()));  // Convertendo LocalDate para java.sql.Date
        ps.setString(3, aula.getConteudo());
    }

    // Implementa o SQL de UPDATE
    @Override
    protected String getUpdateSql(Aula aula) {
        return "UPDATE aula SET quantidade_falta = ?, data = ?, conteudo = ? WHERE id = ?";
    }

    // Define os parâmetros para o UPDATE
    @Override
    protected void setUpdateParameters(PreparedStatement ps, Aula aula) throws SQLException {
        ps.setInt(1, aula.getQuantidadeFalta());
        ps.setDate(2, Date.valueOf(aula.getData()));  // Convertendo LocalDate para java.sql.Date
        ps.setString(3, aula.getConteudo());
        ps.setLong(4, aula.getId());
    }

    // Mapeia o ResultSet para a entidade Aula
    @Override
    protected Aula mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        Integer quantidadeFalta = rs.getInt("quantidade_falta");
        LocalDate data = rs.getDate("data").toLocalDate();  // Convertendo java.sql.Date para LocalDate
        String conteudo = rs.getString("conteudo");

        // Criando uma nova instância de Aula e definindo os valores através dos setters
        Aula aula = new Aula();  // Usando o construtor padrão
        aula.setId(id);
        aula.setQuantidadeFalta(quantidadeFalta);
        aula.setData(String.valueOf(data));  // Passando LocalDate diretamente //n sei se funcionou
        aula.setConteudo(conteudo);

        return aula;
    }
}
