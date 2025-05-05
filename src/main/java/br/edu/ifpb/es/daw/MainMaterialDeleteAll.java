package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.MaterialDAO;
import br.edu.ifpb.es.daw.dao.impl.MaterialDAOImpl;
import br.edu.ifpb.es.daw.entities.Material;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainMaterialDeleteAll {
    public static void main(String[] args) throws DawException {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
            MaterialDAO dao = new MaterialDAOImpl(emf);        
            for (Material mat : dao.getAll()) {
                dao.delete(mat.getId());
            }
            System.out.println("Todos os materiais foram deletados com sucesso!");

        } 
	}
}
