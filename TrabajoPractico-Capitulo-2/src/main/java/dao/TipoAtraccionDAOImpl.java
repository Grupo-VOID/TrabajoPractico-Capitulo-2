package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionProvider;

public class TipoAtraccionDAOImpl {

	public static String buscarPorId(int id) {
		try {
			String sql = "SELECT nombre_tematica FROM tematica_atraccion WHERE id_tematica = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			String tematica = resultados.getString("tematica_atraccion");

			return tematica;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
