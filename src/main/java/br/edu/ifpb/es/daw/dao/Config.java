package br.edu.ifpb.es.daw.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static Properties loadConfig() throws IOException {
        Properties properties = new Properties();

        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Arquivo config.properties não encontrado no classpath");
            }
            properties.load(input);
        }

        return properties;
    }
}
