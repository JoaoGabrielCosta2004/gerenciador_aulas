package br.edu.ifpb.es.daw.dao.impl;

import com.mongodb.client.*;

import br.edu.ifpb.es.daw.entities.NotaBackup;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


//Encontrar um jeito de implementar os métodos dessa classe no NotaBackupDAOImpl!!!!
public class NotaBackupMongo {
    private final MongoCollection<Document> collection;

    public NotaBackupMongo(String dbName, String collectionName) {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://dbuser:ALdidldi3FGOFilo@gerenciadoraulas.0ew11m5.mongodb.net/?retryWrites=true&w=majority&appName=gerenciadorAulas");
        MongoDatabase database = mongoClient.getDatabase(dbName);
        this.collection = database.getCollection(collectionName);
    }

    public void salvar(NotaBackup nota) {
        collection.insertOne(nota.toDocument());
    }

    public List<NotaBackup> listarTodas() {
        List<NotaBackup> lista = new ArrayList<>();
        FindIterable<Document> documentos = collection.find();
        for (Document doc : documentos) {
            lista.add(NotaBackup.fromDocument(doc));
        }
        return lista;
    }

    public void deletarPorMatricula(String matricula) {
        collection.deleteMany(new Document("alunoMatricula", matricula));
    }

    public void atualizarNota(String matricula, String novaNota) {
        Document filtro = new Document("alunoMatricula", matricula);
        Document atualizacao = new Document("$set", new Document("nota", novaNota));
        collection.updateOne(filtro, atualizacao);
    }
}
