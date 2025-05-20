package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.MaterialDAO;
import br.edu.ifpb.es.daw.entities.Material;

import java.sql.*;

public class MaterialDAOImpl extends AbstractDAOImpl<Material, Long> implements MaterialDAO {

    public MaterialDAOImpl() {
        super("material");
    }

    @Override
    protected String getInsertSql(Material material) {
        return "INSERT INTO material (tipo, titulo, link) VALUES (?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, Material material) throws SQLException {
        ps.setString(1, material.getTipo());
        ps.setString(2, material.getTitulo());
        ps.setString(3, material.getLink());
    }

    @Override
    protected String getUpdateSql(Material material) {
        return "UPDATE material SET tipo = ?, titulo = ?, link = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, Material material) throws SQLException {
        ps.setString(1, material.getTipo());
        ps.setString(2, material.getTitulo());
        ps.setString(3, material.getLink());
        ps.setLong(4, material.getId());
    }


    @Override
    protected Material mapResultSetToEntity(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String tipo = rs.getString("tipo");
        String titulo = rs.getString("titulo");
        String link = rs.getString("link");


        Material material = new Material();
        material.setId(id);
        material.setTipo(tipo);
        material.setTitulo(titulo);
        material.setLink(link);

        return material;
    }
}
