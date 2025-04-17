package org.gerenciador.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://ep-restless-grass-a5rf26jr-pooler.us-east-2.aws.neon.tech/neondb?user=neondb_owner&password=npg_MXnT53jBrqbl&sslmode=require";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
