package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.ProjetoDAO;
import br.edu.ifpb.es.daw.dao.impl.ProjetoDAOImpl;
import br.edu.ifpb.es.daw.entities.ProjetoGrande;
import br.edu.ifpb.es.daw.entities.ProjetoPequeno;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MainProjetoSave {

	public static void main(String[] args) throws DawException {
		try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
			ProjetoDAO projetoDAO = new ProjetoDAOImpl(emf);

			ProjetoPequeno pequeno = new ProjetoPequeno();
			pequeno.setNome("Projeto Pintura");
			pequeno.setPrazoMaximoConclusao(LocalDate.of(2025, 10, 1));

			ProjetoGrande grande = new ProjetoGrande();
			grande.setNome("Projeto Campus Novo");
			grande.setOrcamento(new BigDecimal("2500000.00"));

			projetoDAO.save(pequeno);
			projetoDAO.save(grande);
		}
	}
}
