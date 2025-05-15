package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.MaterialDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.MaterialDAOImpl;
import br.edu.ifpb.es.daw.entities.Material;


public class MainMaterialSave {
    public static void main(String[] args) throws PersistenciaDawException {
        MaterialDAO dao = new MaterialDAOImpl();

        Material material = new Material();

        material.setTitulo("Prova matematica 2° ano");
        material.setTipo("Prova");
        material.setLink("ainda sem link");

        System.out.println("Material salvo com sucesso!");

        dao.save(material);

    }
}
