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

	private Connection connection;
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

	protected Connection getConnectionInstance() {
		return this.connection;
	}

	protected abstract String getInsertSql(E obj);

	protected abstract void setInsertParameters(PreparedStatement ps, E obj) throws SQLException;

	protected abstract String getUpdateSql(E obj);

	protected abstract void setUpdateParameters(PreparedStatement ps, E obj) throws SQLException;

	protected String getDeleteSql() {
		return "DELETE FROM " + tableName + " WHERE id = ?";
	}

	protected String getSelectByIdSql() {
		return "SELECT * FROM " + tableName + " WHERE id = ?";
	}

	protected String getSelectAllSql() {
		return "SELECT * FROM " + tableName;
	}

	protected abstract E mapResultSetToEntity(ResultSet rs) throws SQLException;

	protected ResultSet executeQuery(String sql, Object... params) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i + 1, params[i]);
		}
		return ps.executeQuery();
	}

	private Connection getConnection() throws IOException, SQLException{
			Properties properties = Config.loadConfig();

			String url = properties.getProperty("db.url");
			String usuario = properties.getProperty("db.usuario");
			String senha = properties.getProperty("db.senha");
			return DriverManager.getConnection(url, usuario, senha);
	}

	@Override
	public void restartSequence() throws PersistenciaDawException{
		String sql = "ALTER SEQUENCE public."+ tableName + "_id_seq RESTART 1";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaDawException("Erro ao resetar a sequencia", e);
		} 
	}
}
