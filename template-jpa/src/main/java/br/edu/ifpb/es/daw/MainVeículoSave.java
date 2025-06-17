package br.edu.ifpb.es.daw;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainVeículoSave {

	public static void main(String[] args) throws DawException {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
			// TODO: Nesta classe, use a interface VeículoDAO e a classe VeículoDAOImpl
			//  implementada para armazenar uma instância de cada classe presente na
			//  hierarquia. Como JPA suporta Herança, um único DAO é suficiente para a
			//  operação básica de salvar todas as classes da hierarquia.
		}
	}

}
