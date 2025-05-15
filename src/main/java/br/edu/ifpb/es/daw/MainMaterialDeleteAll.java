package br.edu.ifpb.es.daw;

import java.util.List;

import br.edu.ifpb.es.daw.dao.MaterialDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.MaterialDAOImpl;
import br.edu.ifpb.es.daw.entities.Material;

public class MainMaterialDeleteAll {
    public static void main(String[] args) throws PersistenciaDawException {
        MaterialDAO dao = new MaterialDAOImpl();
        List<Material> lista = dao.getAll();

        for (Material mat : lista) {
            dao.delete(mat.getId());
        }
    }
}
