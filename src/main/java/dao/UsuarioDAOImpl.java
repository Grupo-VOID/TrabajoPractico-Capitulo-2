package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import jdbc.ConnectionProvider;
import model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	public List<Usuario> findAll() {
		try {
			String sql = "SELECT *\r\n"
					+ "FROM usuarios\r\n"
					+ "JOIN tematicas_atracciones ta ON ta.id_tematica = usuarios.id_tematica_preferida\r\n"
					+ "GROUP BY usuarios.id_usuario";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> listaUsuarios = new LinkedList<Usuario>();
			while (resultados.next()) {
				listaUsuarios.add(toUsuario(resultados));
			}
			return listaUsuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int updateUsuario(Usuario usuario) {
		try {
			String sql = "UPDATE usuarios SET dinero_disponible = ?, tiempo_disponible = ? WHERE nombre_usuario = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, usuario.getMonedasDisponibles());
			statement.setDouble(2, usuario.getTiempoDisponible());
			statement.setString(3, usuario.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Usuario buscarPorId(int id) {
		try {
			String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Usuario usuario = null;

			if (resultados.next()) {
				usuario = toUsuario(resultados);
			}
			return usuario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

//	public int encontrarIdUsuario(Usuario usuario) {
//		try {
//			String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
//			Connection conn = ConnectionProvider.getConnection();
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.setString(1, usuario.getNombre());
//			ResultSet resultados = statement.executeQuery();
//
//			int id = 0;
//
//			if (resultados.next()) {
//				id = resultados.getInt("id_usuario");
//			}
//			return id;
//		} catch (Exception e) {
//			throw new MissingDataException(e);
//		}
//	}

	private Usuario toUsuario(ResultSet resultados) throws SQLException {

		int id = resultados.getInt("id_usuario");
		String nombre = resultados.getString("nombre_usuario");
		String tematica = resultados.getString("nombre_tematica");
		int dinero = resultados.getInt("dinero_disponible");
		double tiempo = resultados.getInt("tiempo_disponible");
		Usuario usuario = new Usuario(id, nombre, tematica, dinero, tiempo);
		return usuario;
	}
}
