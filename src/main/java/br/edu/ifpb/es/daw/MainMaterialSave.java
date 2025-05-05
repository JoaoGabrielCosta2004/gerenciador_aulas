package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.MaterialDAO;
import br.edu.ifpb.es.daw.dao.impl.MaterialDAOImpl;
import br.edu.ifpb.es.daw.entities.Material;
import br.edu.ifpb.es.daw.entities.Turma;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainMaterialSave {
    public static void main(String[] args) throws DawException {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){
            MaterialDAO dao = new MaterialDAOImpl(emf);
            Material material = new Material();

            material.setTitulo("Prova matematica 5° ano");
            material.setTipo("Prova");
            material.setLink("ainda sem link");

            System.out.println("Material salvo com sucesso!");

            dao.save(material);
        }
    }
}
