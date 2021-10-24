package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Itinerario;
import model.Promocion;
import model.Usuario;


public class ItinerarioDAOImpl {
	public int insertAtraccion(Usuario usuario, Atraccion atraccion) throws SQLException {
		String sql = "INSERT INTO itinerarios (id_usuario, id_atraccion_comprada) VALUES (?, ?)";
		Connection conn = ConnectionProvider.getConnection();
		
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionesDAO();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, usuarioDAO.encontrarIdUsuario(usuario));
		statement.setInt(2, atraccionDAO.encontrarIdAtraccion(atraccion));
		int rows = statement.executeUpdate();

		return rows;
	}
	
	public int insertPromocion(Usuario usuario, Promocion promocion) throws SQLException {
		String sql = "INSERT INTO itinerarios (id_usuario, id_promocion_comprada) VALUES (?, ?)";
		Connection conn = ConnectionProvider.getConnection();
		
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		PromocionDAO promocionDAO = DAOFactory.getPromocionesDAO();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, usuarioDAO.encontrarIdUsuario(usuario));
		statement.setInt(2, promocionDAO.encontrarIdPromocion(promocion));
		int rows = statement.executeUpdate();

		return rows;
	}
	
	public List<Itinerario> buscarPorUsuario(Usuario usuario) throws SQLException {
		String sql = "SELECT * FROM itinerario WHERE id_usuario = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		
		statement.setInt(1, usuarioDAO.encontrarIdUsuario(usuario));
		ResultSet resultados = statement.executeQuery();
		
		Itinerario itinerario = new Itinerario();
		while (resultados.next()) {
			//itinerario[].add(resultados.getInt("id_usuario"), resultados.getInt("id_atraccion_comprada"),
		//			resultados.getInt("id_promocion_comprada"));
		}
		return (List<Itinerario>) itinerario;
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
