package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Adquirible;
import model.Atraccion;

public class AtraccionDAOImpl implements AtraccionDAO {

	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT * \r\n"
					+ "FROM atracciones\r\n"
					+ "JOIN tematicas_atracciones ta ON ta.id_tematica = atracciones.id_tematica\r\n"
					+ "GROUP BY atracciones.id_atraccion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> listaAtracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				listaAtracciones.add(toAtraccion(resultados));
			}
			return listaAtracciones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int updateCupoActual(Atraccion atraccion) {
		try {
			String sql = "UPDATE atracciones SET cupo_actual = ? WHERE nombre_atraccion = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getCupoMaximo() - atraccion.getCupoActual());
			statement.setString(2, atraccion.getNombre());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Atraccion buscarPorId(int id) {
		try {
			String sql = "SELECT * \r\n"
					+ "FROM atracciones \r\n"
					+ "JOIN tematicas_atracciones ta ON ta.id_tematica = atracciones.id_tematica\r\n"
					+ "WHERE id_atraccion = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}
			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int encontrarIdAtraccion(Adquirible atraccion) {
		try {
			String sql = "SELECT * FROM atracciones WHERE nombre_atraccion = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			ResultSet resultados = statement.executeQuery();

			int id = 0;

			if (resultados.next()) {
				id = resultados.getInt("id_atraccion");
			}
			return id;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion toAtraccion(ResultSet resultados) throws SQLException {

		String nombre = resultados.getString("nombre_atraccion");
		String tematica = resultados.getString("nombre_tematica");
		double monedas = resultados.getInt("costo");
		double tiempo = resultados.getInt("duracion");
		int cupo = resultados.getInt("cupo_actual");

		Atraccion atraccion = new Atraccion(nombre, tematica, monedas, tiempo, cupo);

		return atraccion;
	}
}