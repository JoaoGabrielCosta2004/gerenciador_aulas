package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.EmpregadoDAO;
import br.edu.ifpb.es.daw.dao.impl.EmpregadoDAOImpl;
import br.edu.ifpb.es.daw.entities.Empregado;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainEmpregadoDeleteAll {

	public static void main(String[] args) throws DawException {
		try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
			EmpregadoDAO empregadoDAO = new EmpregadoDAOImpl(emf);
			List<Empregado> empregados = empregadoDAO.getAll();
			for (Empregado empregado : empregados) {
				empregadoDAO.delete(empregado.getId());
			}
		}
	}
}