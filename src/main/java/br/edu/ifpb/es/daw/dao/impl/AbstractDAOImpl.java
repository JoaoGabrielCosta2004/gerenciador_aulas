package br.edu.ifpb.es.daw.dao.impl;

import br.edu.ifpb.es.daw.Config;
import br.edu.ifpb.es.daw.dao.DAO;
import br.edu.ifpb.es.daw.dao.PersistenciaDawException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class AbstractDAOImpl<E, T> implements DAO<E, T> {

	/*private*/ Connection connection;
	private String tableName;

	public AbstractDAOImpl(String tableName) {
		this.tableName = tableName;
		try {
			this.connection = getConnection();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
            System.out.println("Erro ao carregar as configurações.");
		}
	}

	// Método para salvar uma entidade no banco de dados
	@Override
	public void save(E obj) throws PersistenciaDawException {
		String sql = getInsertSql(obj);
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			setInsertParameters(ps, obj);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaDawException("Erro ao salvar a entidade.", e);
		}
	}

	// Método para atualizar uma entidade no banco de dados
	@Override
	public E update(E obj) throws PersistenciaDawException {
		String sql = getUpdateSql(obj);
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			setUpdateParameters(ps, obj);
			ps.executeUpdate();
			return obj;
		} catch (SQLException e) {
			throw new PersistenciaDawException("Erro ao atualizar a entidade.", e);
		}
	}

	// Método para deletar uma entidade do banco de dados
	@Override
	public void delete(T primaryKey) throws PersistenciaDawException {
		String sql = getDeleteSql();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setObject(1, primaryKey);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaDawException("Erro ao deletar a entidade.", e);
		}
	}

	// Método para recuperar uma entidade pelo ID
	@Override
	public E getByID(T primaryKey) throws PersistenciaDawException {
		String sql = getSelectByIdSql();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setObject(1, primaryKey);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return mapResultSetToEntity(rs);
			}
			return null;
		} catch (SQLException e) {
			throw new PersistenciaDawException("Erro ao recuperar a entidade pelo ID.", e);
		}
	}

	// Método para recuperar todas as entidades da tabela
	@Override
	public List<E> getAll() throws PersistenciaDawException {
		String sql = getSelectAllSql();
		List<E> list = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(mapResultSetToEntity(rs));
			}
		} catch (SQLException e) {
			throw new PersistenciaDawException("Erro ao recuperar todas as entidades.", e);
		}
		return list;
	}

	// Método abstrato para gerar o SQL de INSERT
	protected abstract String getInsertSql(E obj);

	// Método abstrato para definir os parâmetros do INSERT
	protected abstract void setInsertParameters(PreparedStatement ps, E obj) throws SQLException;

	// Método abstrato para gerar o SQL de UPDATE
	protected abstract String getUpdateSql(E obj);

	// Método abstrato para definir os parâmetros do UPDATE
	protected abstract void setUpdateParameters(PreparedStatement ps, E obj) throws SQLException;

	// Método para gerar o SQL de DELETE
	protected String getDeleteSql() {
		return "DELETE FROM " + tableName + " WHERE id = ?";
	}

	// Método para gerar o SQL de SELECT por ID
	protected String getSelectByIdSql() {
		return "SELECT * FROM " + tableName + " WHERE id = ?";
	}

	// Método para gerar o SQL de SELECT ALL
	protected String getSelectAllSql() {
		return "SELECT * FROM " + tableName;
	}

	// Método abstrato para mapear o ResultSet para uma entidade E
	protected abstract E mapResultSetToEntity(ResultSet rs) throws SQLException;

	private Connection getConnection() throws IOException, SQLException{
		// Carregar as configurações do arquivo
			Properties properties = Config.loadConfig();
				// Usar as configurações para a conexão
			String url = properties.getProperty("db.url");
			String usuario = properties.getProperty("db.usuario");
			String senha = properties.getProperty("db.senha");
			return DriverManager.getConnection(url, usuario, senha);
	}
}
