package br.edu.ifpb.es.daw.app.service;

import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.ProfessorDAOImpl;
import br.edu.ifpb.es.daw.entities.Professor;

public class LoginService {

    private final ProfessorDAOImpl professorDAO = new ProfessorDAOImpl();

    public String login(String email, String senha) throws PersistenciaDawException {
        String coordEmail = System.getenv("COORD_EMAIL");
        String coordSenha = System.getenv("COORD_SENHA");

        if (coordEmail != null && coordSenha != null) {
            if (coordEmail.equals(email) && coordSenha.equals(senha)) {
                return "COORDENADOR";
            }
        }

        try {
            Professor professor = professorDAO.findByEmail(email);
            if (professor != null && professor.getSenha().equals(senha)) {
                return "PROFESSOR";
            }
        } catch (Exception e) {
            throw new PersistenciaDawException("Erro ao verificar professor no banco", e);
        }

        return null;
    }
}