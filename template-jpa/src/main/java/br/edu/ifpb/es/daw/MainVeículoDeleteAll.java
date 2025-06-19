package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.VeiculoDAO;
import br.edu.ifpb.es.daw.dao.impl.VeiculoDAOImpl;
import br.edu.ifpb.es.daw.entities.Veiculo;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainVeículoDeleteAll {

	public static void main(String[] args) throws DawException {
		try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
			VeiculoDAO veiculoDAO = new VeiculoDAOImpl(emf);
			List<Veiculo> veiculos = veiculoDAO.getAll();
			for (Veiculo v : veiculos) {
				veiculoDAO.delete(v.getId());
			}
		}
	}
}