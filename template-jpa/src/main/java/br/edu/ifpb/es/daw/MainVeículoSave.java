package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.VeiculoDAO;
import br.edu.ifpb.es.daw.dao.impl.VeiculoDAOImpl;
import br.edu.ifpb.es.daw.entities.Bicicleta;
import br.edu.ifpb.es.daw.entities.Caminhao;
import br.edu.ifpb.es.daw.entities.Carro;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainVeículoSave {

	public static void main(String[] args) throws DawException {
		try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
			VeiculoDAO veiculoDAO = new VeiculoDAOImpl(emf);

			Caminhao caminhao = new Caminhao();
			caminhao.setNome("TruckMaster");
			caminhao.setConstrutora("Volvo");
			caminhao.setCargaMaximaKg(30000);
			caminhao.setNumeroDeVagões(3);

			Carro carro = new Carro();
			carro.setNome("Sedan LX");
			carro.setConstrutora("Honda");
			carro.setNumeroMaximoPassageiros(5);
			carro.setNumeroDePortas(4);

			Bicicleta bicicleta = new Bicicleta();
			bicicleta.setNome("Mountain Bike");
			bicicleta.setConstrutora("Caloi");
			bicicleta.setNumeroMaximoPassageiros(1);
			bicicleta.setAlturaDoSelimEmCm(75);

			veiculoDAO.save(caminhao);
			veiculoDAO.save(carro);
			veiculoDAO.save(bicicleta);
		}
	}
}