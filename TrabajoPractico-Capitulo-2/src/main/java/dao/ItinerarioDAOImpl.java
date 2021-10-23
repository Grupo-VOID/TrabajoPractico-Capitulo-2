package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Itinerario;


public class ItinerarioDAOImpl {
	/*public int insertAtraccion(Itinerario itinerario) throws SQLException {
		String sql = "INSERT INTO itinerarios (id_usuario, id_atraccion_comprada) VALUES (?, ?)";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, itinerario.getId_usuario());
		statement.setInt(2, itinerario.getId_atraccion_comprada());
		int rows = statement.executeUpdate();

		return rows;
	}
	
	public int insertPromocion(Itinerario itinerario) throws SQLException {
		String sql = "INSERT INTO itinerarios (id_usuario, id_promocion_comprada) VALUES (?, ?)";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, itinerario.getId_usuario());
		statement.setInt(2, itinerario.getId_promocion_comprada());
		int rows = statement.executeUpdate();

		return rows;
	}*/
	
	public List<Itinerario> buscarPorIdUsuario(int id) throws SQLException {
		String sql = "SELECT * FROM itinerario WHERE id_usuario = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultados = statement.executeQuery();
		
		List<Itinerario> itinerario = new LinkedList<Itinerario>();
		while (resultados.next()) {
			//itinerario.add(toItinerario(resultados));
		}
		return itinerario;
	}
	
	public int countAll() throws SQLException {
		String sql = "SELECT count(*) AS 'total' FROM itinerario";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("total");

		return total;
	}

	public List<Itinerario> findAll() throws SQLException {
		String sql = "SELECT * FROM itinerario";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		List<Itinerario> itinerario = new LinkedList<Itinerario>();
		while (resultados.next()) {
			//itinerario.add(toItinerario(resultados));
		}
		return itinerario;
	}

	/*private Itinerario toItinerario(ResultSet resultados) throws SQLException {
		return new Itinerario(resultados.getInt("id_usuario"), resultados.getInt("id_atraccion_comprada"),
				resultados.getInt("id_promocion_comprada"));
	}*/
}
