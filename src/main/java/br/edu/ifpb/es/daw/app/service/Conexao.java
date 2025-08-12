package br.edu.ifpb.es.daw.app.service;

import br.edu.ifpb.es.daw.dao.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

    private static Connection conexao;

    public static Connection getConexao() throws SQLException {
        if (conexao == null || conexao.isClosed()) {
            try {
                Properties props = Config.loadConfig();
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.usuario");
                String password = props.getProperty("db.senha");

                conexao = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                throw new SQLException("Erro ao obter conexão: " + e.getMessage(), e);
            }
        }
        return conexao;
    }
}
