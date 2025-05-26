package br.edu.ifpb.es.daw.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import java.io.InputStream;

public class MinioService {
    private final MinioClient minioClient;
    private final String bucketName = "material";

    public MinioService() {
        this.minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("minioadmin", "minioadmin")
                .build();
    }

    public void uploadFile(InputStream inputStream, String objectName, long size) throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(inputStream, size, -1)
                        .contentType("application/pdf")
                        .build()
        );
    }
}
