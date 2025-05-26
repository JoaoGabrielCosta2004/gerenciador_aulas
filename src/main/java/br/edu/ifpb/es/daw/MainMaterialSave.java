package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.MaterialDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.MaterialDAOImpl;
import br.edu.ifpb.es.daw.entities.Material;
import br.edu.ifpb.es.daw.service.MinioService;

import java.io.File;
import java.io.FileInputStream;


public class MainMaterialSave {
    public static void main(String[] args) throws Exception {
        MaterialDAO dao = new MaterialDAOImpl();
        MinioService minioService = new MinioService();

        Material material = new Material();

        material.setTitulo("Prova matematica 2° ano");
        material.setTipo("Prova");

        File arquivo = new File("C:/Users/FLY-PC/Downloads/cardapio att (3).pdf");
        String nomeArquivo = arquivo.getName();

        try (FileInputStream fis = new FileInputStream(arquivo)) {
            minioService.uploadFile(fis, nomeArquivo, arquivo.length());
        }

        material.setLink(nomeArquivo);

        dao.save(material);

        System.out.println("✅ Material salvo com sucesso!");
    }
}
