package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;

public class TipoAtraccionDAOImpl implements TipoAtraccionDAO{

	public String buscarPorId(int id) {
		try {
			String sql = "SELECT nombre_tematica FROM tematicas_atracciones WHERE id_tematica = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			String tematica = resultados.getString("nombre_tematica");

			return tematica;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<String> findAll() {
		return null;
	}
}
