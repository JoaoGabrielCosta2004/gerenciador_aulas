package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.entities.Aula;

import java.sql.*;
import java.time.LocalDate;

public class AulaDAOImpl extends AbstractDAOImpl<Aula, Long> implements AulaDAO {

    public AulaDAOImpl() {
        super("aula");
    }

    @Override
    protected String getInsertSql(Aula aula) {
        return "INSERT INTO aula (quantidadefalta, data, conteudo) VALUES (?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Aula aula) throws SQLException {
        ps.setInt(1, aula.getQuantidadeFalta());
        ps.setDate(2, Date.valueOf(aula.getData()));
        ps.setString(3, aula.getConteudo());
    }

    @Override
    protected String getUpdateSql(Aula aula) {
        return "UPDATE aula SET quantidadefalta = ?, data = ?, conteudo = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Aula aula) throws SQLException {
        ps.setInt(1, aula.getQuantidadeFalta());
        ps.setDate(2, Date.valueOf(aula.getData()));
        ps.setString(3, aula.getConteudo());
        ps.setLong(4, aula.getId());
    }

    @Override
    protected Aula mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        Integer quantidadeFalta = rs.getInt("quantidadefalta");
        LocalDate data = rs.getDate("data").toLocalDate();
        String conteudo = rs.getString("conteudo");


        Aula aula = new Aula();
        aula.setId(id);
        aula.setQuantidadeFalta(quantidadeFalta);
        aula.setData(String.valueOf(data));
        aula.setConteudo(conteudo);

        return aula;
    }
}
