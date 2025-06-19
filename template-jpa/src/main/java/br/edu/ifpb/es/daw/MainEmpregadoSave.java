package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.EmpregadoDAO;
import br.edu.ifpb.es.daw.dao.impl.EmpregadoDAOImpl;
import br.edu.ifpb.es.daw.entities.EmpregadoTempoIntegral;
import br.edu.ifpb.es.daw.entities.EmpregadoTempoParcial;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class MainEmpregadoSave {

	public static void main(String[] args) throws DawException {
		try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
			EmpregadoDAO empregadoDAO = new EmpregadoDAOImpl(emf);

			EmpregadoTempoIntegral integral = new EmpregadoTempoIntegral();
			integral.setNome("Carlos Silva");
			integral.setSalario(new BigDecimal("4500.00"));

			EmpregadoTempoParcial parcial = new EmpregadoTempoParcial();
			parcial.setNome("Ana Paula");
			parcial.setValorHora(new BigDecimal("30.00"));

			empregadoDAO.save(integral);
			empregadoDAO.save(parcial);
		}
	}
}
