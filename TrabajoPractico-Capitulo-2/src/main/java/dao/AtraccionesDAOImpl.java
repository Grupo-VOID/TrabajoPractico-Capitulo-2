package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import main.java.model.Promocion;
import model.Atraccion;

public class AtraccionesDAOImpl implements AtraccionesDAO {

	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM ATRACCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> listaAtracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				String nombre = resultados.getString("nombre");
				String tematica = resultados.getString("tipo");
				double monedas = resultados.getInt("costo");
				double tiempo = resultados.getInt("tiempo");
				int cupo = resultados.getInt("cupo");
				Atraccion atraccion = new Atraccion(nombre, tematica, monedas, tiempo, cupo);
				listaAtracciones.add(atraccion);
			}
			return listaAtracciones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int updateCupoActual(Atraccion atraccion) throws SQLException {
		String sql = "UPDATE atraccioones SET cupo_actual = ? WHERE nombre_atraccion = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, atraccion.getCupoActual());
		statement.setString(2, atraccion.getNombre());
		int rows = statement.executeUpdate();

		return rows;
	}

//	public Atraccion buscarPorNombre(String nombre) throws SQLException {
//		String sql = "SELECT * FROM atracciones WHERE nombre_atraccion = ?";
//		Connection conn = ConnectionProvider.getConnection();
//		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setString(1, nombre);
//		ResultSet resultados = statement.executeQuery();
//
//		Atraccion atraccion = null;
//
//		if (resultados.next()) {
//			atraccion = toAtraccion(resultados);
//		}
//		return atraccion;
//	}

	public Atraccion buscarPorId(int id) throws SQLException {
		String sql = "SELECT nombre_atraccion FROM atracciones WHERE id_atraccion = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultados = statement.executeQuery();

		Atraccion atraccion = null;

		if (resultados.next()) {
			atraccion = toAtraccion(resultados);
		}
		return atraccion;
	}
	
	private Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		return new Atraccion(resultados.getString("nombre_atraccion"), resultados.getInt("id_tipo_atraccion"), 
				resultados.getInt("costo"), resultados.getInt("duracion"), resultados.getInt("cupo_actual"));
	}
}