package br.edu.ifpb.es.daw;
import java.util.List;

import br.edu.ifpb.es.daw.dao.NotaBackupDAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;
import br.edu.ifpb.es.daw.dao.impl.NotaBackupDAOImpl;
import br.edu.ifpb.es.daw.dao.impl.NotaBackupMongo;
import br.edu.ifpb.es.daw.entities.NotaBackup;

public class MainMongoTeste {
    public static void main(String[] args) throws PersistenciaDawException {
        NotaBackupDAO bdJDBC = new NotaBackupDAOImpl();
        NotaBackupMongo dao = new NotaBackupMongo("escola", "notas_backup");

        // Inserção
        for (NotaBackup tab : bdJDBC.getAll()) {
            dao.salvar(tab);
        }
        System.out.println("Todas as notas registradas");

        // Listagem em formato de tabela
        List<NotaBackup> notas = dao.listarTodas();
        for (NotaBackup n : notas) {
            System.out.println(n);
        }
        System.out.println("+------------+-----------------+--------------+-----------------+");

        // Atualização de nota
        dao.atualizarNota("20240001", "9.8");

        // Deletar por matrícula
        // dao.deletarPorMatricula("20240001");
    }
}