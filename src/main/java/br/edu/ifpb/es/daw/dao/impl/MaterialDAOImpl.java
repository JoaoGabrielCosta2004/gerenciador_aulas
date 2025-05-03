package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.dao.MaterialDAO;
import br.edu.ifpb.es.daw.entities.Material;
import jakarta.persistence.EntityManagerFactory;

public class MaterialDAOImpl extends AbstractDAOImpl<Material, Long> implements MaterialDAO {
    public MaterialDAOImpl(EntityManagerFactory emf) {
        super(Material.class, emf);
    }
}