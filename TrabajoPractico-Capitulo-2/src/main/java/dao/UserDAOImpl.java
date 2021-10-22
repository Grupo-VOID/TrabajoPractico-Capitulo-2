package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Usuario;

public class UserDAOImpl implements UserDAO {

//	public int insert(User user) {
//		try {
//			String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";
//			Connection conn = ConnectionProvider.getConnection();
//
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.setString(1, user.getUsername());
//			statement.setString(2, user.getPassword());
//			int rows = statement.executeUpdate();
//
//			return rows;
//		} catch (Exception e) {
//			throw new MissingDataException(e);
//		}
//	}
//
//	public int update(User user) {
//		try {
//			String sql = "UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?";
//			Connection conn = ConnectionProvider.getConnection();
//
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.setString(1, user.getPassword());
//			statement.setString(2, user.getUsername());
//			int rows = statement.executeUpdate();
//
//			return rows;
//		} catch (Exception e) {
//			throw new MissingDataException(e);
//		}
//	}
//
//	public int delete(User user) {
//		try {
//			String sql = "DELETE FROM USERS WHERE USERNAME = ?";
//			Connection conn = ConnectionProvider.getConnection();
//
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.setString(1, user.getUsername());
//			int rows = statement.executeUpdate();
//
//			return rows;
//		} catch (Exception e) {
//			throw new MissingDataException(e);
//		}
//	}
//
//	public User findByUsername(String username) {
//		try {
//			String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
//			Connection conn = ConnectionProvider.getConnection();
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.setString(1, username);
//			ResultSet resultados = statement.executeQuery();
//
//			User user = null;
//
//			if (resultados.next()) {
//				user = toUser(resultados);
//			}
//
//			return user;
//		} catch (Exception e) {
//			throw new MissingDataException(e);
//		}
//	}
//
//	public int countAll() {
//		try {
//			String sql = "SELECT COUNT(1) AS TOTAL FROM USERS";
//			Connection conn = ConnectionProvider.getConnection();
//			PreparedStatement statement = conn.prepareStatement(sql);
//			ResultSet resultados = statement.executeQuery();
//
//			resultados.next();
//			int total = resultados.getInt("TOTAL");
//
//			return total;
//		} catch (Exception e) {
//			throw new MissingDataException(e);
//		}
//	}

	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM USUARIOS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> listaUsuarios = new LinkedList<Usuario>();
			while (resultados.next()) {
				String nombre = resultados.getString("nombre");
				String tematica = resultados.getString("atraccion_favorita");
				int monedas = resultados.getInt("cantidad_monedas");
				double tiempo = resultados.getInt("tiempo_disponible");
				Usuario usuario = new Usuario(nombre, tematica, monedas, tiempo);
				listaUsuarios.add(usuario);
			}
			return listaUsuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
//
//	public List<Usuario> GenerarAtracciones() {
//		try {
//			String sql = "SELECT * FROM ATRACCIONES";
//			Connection conn = ConnectionProvider.getConnection();
//			PreparedStatement statement = conn.prepareStatement(sql);
//			ResultSet resultados = statement.executeQuery();
//
//			List<Usuario> usuarios = new LinkedList<Usuario>();
//			while (resultados.next()) {
//				usuarios.add(toAtraccion(resultados));
//			}
//
//			return usuarios;
//		} catch (Exception e) {
//			throw new MissingDataException(e);
//		}
//	}
//
//	private Usuario toAtraccion(ResultSet resultados) throws SQLException {
//		return new Usuario(resultados.getString(1), resultados.getString(2));
//	}
//
//	public List<Usuario> GenerarPromociones() {
//		try {
//			String sql = "SELECT * FROM PROMOCIONES";
//			Connection conn = ConnectionProvider.getConnection();
//			PreparedStatement statement = conn.prepareStatement(sql);
//			ResultSet resultados = statement.executeQuery();
//
//			List<Usuario> usuarios = new LinkedList<Usuario>();
//			while (resultados.next()) {
//				usuarios.add(toUser(resultados));
//			}
//
//			return usuarios;
//		} catch (Exception e) {
//			throw new MissingDataException(e);
//		}
//	}
//
//	private Usuario toPromocion(ResultSet resultados) throws SQLException {
//		return new Usuario(resultados.getString(1), resultados.getString(2));
//	}
//}
