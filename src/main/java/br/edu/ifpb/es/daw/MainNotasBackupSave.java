package br.edu.ifpb.es.daw;


import br.edu.ifpb.es.daw.dao.NotaBackupDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.NotaBackupDAOImpl;
import br.edu.ifpb.es.daw.entities.NotaBackup;

import java.util.List;

public class MainNotasBackupSave {
    public static void main(String[] args) {
        // Criar DAO
        NotaBackupDAO notaBackupDAO = new NotaBackupDAOImpl();

        // Obter dadosgit
        List<NotaBackup> notas;
        try {
            notas = ((NotaBackupDAOImpl) notaBackupDAO).getAllNotasCompletas();
            System.out.println("Notas recuperadas com sucesso!");
        } catch (PersistenciaDawException e) {
            System.err.println("Erro ao recuperar as notas: " + e.getMessage());
            return;
        }

        // Exibir os dados no console
        for (NotaBackup nota : notas) {
            System.out.println("---------------");
            System.out.println("ID: " + nota.getId());
            System.out.println("Nota: " + nota.getNota());
            System.out.println("Aluno: " + nota.getAlunoNome());
            System.out.println("Matrícula: " + nota.getAlunoMatricula());
            System.out.println("Disciplina: " + nota.getDisciplinaNome());
        }
    }
}