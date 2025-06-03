package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.dao.MaterialDAO;
import br.edu.ifpb.es.daw.dao.impl.AulaDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.MaterialDAOImpl;
import br.edu.ifpb.es.daw.entities.Aula;
import br.edu.ifpb.es.daw.entities.Material;
import br.edu.ifpb.es.daw.entities.Turma;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainMaterialSave {
    public static void main(String[] args) throws DawException {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")){
            MaterialDAO materialdao = new MaterialDAOImpl(emf);
            AulaDAO aulaDAO = new AulaDAOImpl(emf);

            Aula aula = aulaDAO.getByID(1L);

            Material material = new Material();
            material.setTitulo("Prova matematica 5° ano");
            material.setTipo("Prova");
            material.setLink("ainda sem link");

            material.setAula(aula);

            materialdao.save(material);

            System.out.println("Material salvo com sucesso!");
        }
    }
}
