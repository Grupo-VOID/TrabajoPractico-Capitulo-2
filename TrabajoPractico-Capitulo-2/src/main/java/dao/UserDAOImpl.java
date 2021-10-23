package dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import jdbc.ConnectionProvider;
import model.Usuario;
import paquete.TipoAtraccion;

public class UserDAOImpl implements UserDAO {

	// esto debería ser por ID pero no se como obtenerlo
	public int updateDineroDisponible(Usuario usuario) throws SQLException {
		String sql = "UPDATE usuarios SET dinero_disponible = ? WHERE nombre_usuario = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setDouble(1, usuario.getMonedasDisponibles());
		statement.setString(2, usuario.getNombre());
		int rows = statement.executeUpdate();

		return rows;
	}

	public int updateTiempoDisponible(Usuario usuario) throws SQLException {
		String sql = "UPDATE usuarios SET tiempo_disponible = ? WHERE nombre_usuario = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setDouble(1, usuario.getTiempoDisponible());
		statement.setString(2, usuario.getNombre());
		int rows = statement.executeUpdate();

		return rows;
	}

	public Usuario buscarPorNombre(String nombre) throws SQLException {
		String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, nombre);
		ResultSet resultados = statement.executeQuery();

		Usuario usuario = null;

		if (resultados.next()) {
			usuario = toUsuario(resultados);
		}
		return usuario;
	}

	public Usuario buscarPorId(int id) throws SQLException {
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
	}

	private Usuario toUsuario(ResultSet resultados) throws SQLException {
		String nombre = resultados.getString("nombre_usuario");
		String tematica = TipoAtraccionDAO.buscarPorId(resultados.getInt("id_tematica_preferida"));
		int dinero = resultados.getInt("dinero_disponible");
		double tiempo = resultados.getInt("tiempo_disponible");
		Usuario usuario = new Usuario(nombre, tematica, dinero, tiempo);
		return usuario;
	}

	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM usuarios";
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
}
