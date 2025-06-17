package br.edu.ifpb.es.daw;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainEmpregadoDeleteAll {

	public static void main(String[] args) throws DawException {
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("daw")) {
			// TODO: Nesta classe, use a interface EmpregadoDAO e a classe EmpregadoDAOImpl
			//  para recuperar todas as instâncias da hierarquia e remover uma a uma
			//  todas elas. Como JPA suporta Herança, recuperar todas as instâncias da
			//  classe raiz da hierarquia recupera as instâncias de todas as classes da
			//  hierarquia e não apenas as instâncias da classe raiz.
		}
	}

}
