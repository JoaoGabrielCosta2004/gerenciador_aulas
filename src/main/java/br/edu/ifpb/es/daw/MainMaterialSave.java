package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.MaterialDAO;
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
        material.setTitulo("teste");
        material.setTipo("teste");
        material.setId_aula(3L);
        material.setAvaliacao(true);

        File arquivo = new File("");
        String nomeArquivo = arquivo.getName();

        try (FileInputStream fis = new FileInputStream(arquivo)) {
            minioService.uploadFile(fis, nomeArquivo, arquivo.length());
        }


        String urlCompartilhamento = minioService.getPresignedUrl(nomeArquivo);
        material.setLink(urlCompartilhamento);


        dao.save(material);

        System.out.println("Material salvo com sucesso!");
        System.out.println("📎 Link: " + urlCompartilhamento);
    }
}

