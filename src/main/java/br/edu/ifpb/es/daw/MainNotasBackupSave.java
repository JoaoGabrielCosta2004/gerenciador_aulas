package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.NotaBackupDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.entities.NotaBackup;
import java.io.File;
import java.io.IOException;
import java.util.List;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainNotasBackupSave {
    public static void main(String[] args) throws PersistenciaDawException {
        // Criando o DAO de NotaBackup
        NotaBackupDAO notaBackupDAO = new NotaBackupDAO();

        // Recuperando todas as notas com os dados completos
        List<NotaBackup> notas;
        try {
            notas = notaBackupDAO.getAllNotasCompletas();
            System.out.println("Notas recuperadas com sucesso!");
        } catch (PersistenciaDawException e) {
            System.err.println("Erro ao recuperar as notas: " + e.getMessage());
            return;
        }

        // Serializando a lista de notas em JSON
        Jsonb jsonb = JsonbBuilder.create();  // Criando o objeto JSON-B

        // Criando o arquivo de saída
        File jsonFile = new File("notas_backup.json");

        try {
            // Convertendo para JSON e salvando no arquivo
            jsonb.toJson(notas, jsonFile);
            System.out.println("Notas salvas em JSON com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar as notas em JSON: " + e.getMessage());
        } finally {
            try {
                jsonb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}