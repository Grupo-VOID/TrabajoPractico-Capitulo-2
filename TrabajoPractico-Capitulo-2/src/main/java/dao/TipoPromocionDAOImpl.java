package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionProvider;

public class TipoPromocionDAOImpl {

	public static String buscarPorId(int id) {
		try {
			String sql = "SELECT tipo_promocion FROM tipo_promociones WHERE id_tipo_promocion = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			String tipo = resultados.getString("tipo_promocion");

			return tipo;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
