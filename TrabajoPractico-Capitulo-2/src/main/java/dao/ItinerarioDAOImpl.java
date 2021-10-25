package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	@Override
	public List<Itinerario> findAll() {
		return null;
	}
}
