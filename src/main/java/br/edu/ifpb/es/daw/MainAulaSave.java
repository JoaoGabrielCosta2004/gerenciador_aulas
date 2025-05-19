package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AulaDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.AulaDAOImpl;
import br.edu.ifpb.es.daw.entities.Aula;


public class MainAulaSave {
    public static void main(String[] args) throws PersistenciaDawException {
        AulaDAO dao = new AulaDAOImpl();

        Aula aula = new Aula();

        aula.setConteudo("Aplicação de prova matematica");
        aula.setData("14/05/2025");
        aula.setQuantidadeFalta(35);

        System.out.println("Aula salva com sucesso");

        dao.save(aula);
    }
}
