package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Adquirible;
import model.Atraccion;
import model.Itinerario;
import model.Usuario;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	public int insertAtraccion(Usuario usuario, Adquirible atraccion) {
		try {
			String sql = "INSERT INTO itinerarios (id_usuario, id_atraccion_comprada) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			AtraccionDAO atraccionDAO = DAOFactory.getAtraccionesDAO();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getID());
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

			PromocionDAO promocionDAO = DAOFactory.getPromocionesDAO();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getID());
			statement.setInt(2, promocionDAO.encontrarIdPromocion(promocion));
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Atraccion> atraccionesUsuario(int id) {
		try {
			String sql = "SELECT *\r\n" + "FROM (\r\n" + "SELECT id_atraccion_comprada AS \"id_atraccion\"\r\n"
					+ "FROM itinerarios\r\n"
					+ "LEFT JOIN atracciones_promociones ON itinerarios.id_promocion_comprada = atracciones_promociones.id_promocion\r\n"
					+ "WHERE id_usuario = ?\r\n" + "\r\n" + "UNION\r\n" + "\r\n"
					+ "SELECT id_atraccion AS \"id_atraccion\"\r\n" + "FROM itinerarios\r\n"
					+ "LEFT JOIN atracciones_promociones ON itinerarios.id_promocion_comprada = atracciones_promociones.id_promocion\r\n"
					+ "WHERE id_usuario = ?\r\n" + "\r\n" + "ORDER BY id_atraccion DESC\r\n" + ")\r\n"
					+ "WHERE id_atraccion IS NOT NULL";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setInt(2, id);
			ResultSet resultados = statement.executeQuery();

			AtraccionDAO atraccionesDAO = DAOFactory.getAtraccionesDAO();

			List<Atraccion> listaAtracciones = new ArrayList<Atraccion>();
			while (resultados.next()) {
				listaAtracciones.add(atraccionesDAO.buscarPorId(resultados.getInt("id_atraccion")));
			}
			return listaAtracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Adquirible> obtenerAdquiribles(int idUsuario) {
		try {
			String sql = "SELECT * FROM itinerarios WHERE id_usuario = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idUsuario);
			ResultSet resultados = statement.executeQuery();

			AtraccionDAO atraccionesDAO = DAOFactory.getAtraccionesDAO();
			PromocionDAO promocionesDAO = DAOFactory.getPromocionesDAO();

			ArrayList<Adquirible> listaAdquiribles = new ArrayList<Adquirible>();
			while (resultados.next()) {
				if (resultados.getObject("id_promocion_comprada") != null) {
					listaAdquiribles.add(promocionesDAO.buscarPorId(resultados.getInt("id_promocion_comprada")));
				}
				if (resultados.getObject("id_atraccion_comprada") != null) {
					listaAdquiribles.add(atraccionesDAO.buscarPorId(resultados.getInt("id_atraccion_comprada")));
				}
			}
			return listaAdquiribles;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Itinerario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
