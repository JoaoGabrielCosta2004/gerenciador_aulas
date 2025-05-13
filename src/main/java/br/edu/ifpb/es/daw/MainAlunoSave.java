package br.edu.ifpb.es.daw;

import br.edu.ifpb.es.daw.dao.AlunoDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.AlunosDAOImpl;
import br.edu.ifpb.es.daw.entities.Aluno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Properties;
import java.time.LocalDate;

public class MainAlunoSave {
    public static void main(String[] args) {
        try {
            // Carregar as configurações do arquivo
            Properties properties = Config.loadConfig();

            // Usar as configurações para a conexão
            String url = properties.getProperty("db.url");
            String usuario = properties.getProperty("db.usuario");
            String senha = properties.getProperty("db.senha");

            // Conexão JDBC
            try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {

                // Criando o DAO
                AlunoDAO dao = new AlunosDAOImpl(connection);

                // Criando um novo aluno
                Aluno aluno = new Aluno();
                aluno.setNome("teste");
                aluno.setDataNascimento(LocalDate.of(2015, 1, 1));
                aluno.setMatricula(System.nanoTime() + "20250510");

                // Salvando o aluno no banco de dados
                dao.save(aluno);

                // Exibindo mensagem de sucesso
                System.out.println("Aluno salvo com sucesso!");

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao conectar ao banco de dados.");
            } catch (PersistenciaDawException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar as configurações.");
        }
    }
}
