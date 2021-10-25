package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Adquirible;
import model.Itinerario;
import model.Usuario;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	public int insertAtraccion(Usuario usuario, Adquirible atraccion) {
		try {
			String sql = "INSERT INTO itinerarios (id_usuario, id_atraccion_comprada) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
			AtraccionDAO atraccionDAO = DAOFactory.getAtraccionesDAO();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuarioDAO.encontrarIdUsuario(usuario));
			statement.setInt(2, atraccionDAO.encontrarIdAtraccion(atraccion));
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insertPromocion(Usuario usuario, Adquirible promocion) {
		try {
			String sql = "INSERT INTO itinerarios (id_usuario, id_promocion_comprada) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
			PromocionDAO promocionDAO = DAOFactory.getPromocionesDAO();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuarioDAO.encontrarIdUsuario(usuario));
			statement.setInt(2, promocionDAO.encontrarIdPromocion(promocion));
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Itinerario> buscarPorUsuario(Usuario usuario) {
		try {
			String sql = "SELECT * FROM itinerario WHERE id_usuario = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();

			statement.setInt(1, usuarioDAO.encontrarIdUsuario(usuario));
			ResultSet resultados = statement.executeQuery();

			Itinerario itinerario = new Itinerario();
			while (resultados.next()) {
				// itinerario[].add(resultados.getInt("id_usuario"),
				// resultados.getInt("id_atraccion_comprada"),
				// resultados.getInt("id_promocion_comprada"));
			}
			return (List<Itinerario>) itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Itinerario> findAll() {
		try {
			String sql = "SELECT * FROM itinerario";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Itinerario> itinerario = new LinkedList<Itinerario>();
			while (resultados.next()) {
				// itinerario.add(toItinerario(resultados));
			}
			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	/*
	 * private Itinerario toItinerario(ResultSet resultados) throws SQLException {
	 * return new Itinerario(resultados.getInt("id_usuario"),
	 * resultados.getInt("id_atraccion_comprada"),
	 * resultados.getInt("id_promocion_comprada")); }
	 */
}
