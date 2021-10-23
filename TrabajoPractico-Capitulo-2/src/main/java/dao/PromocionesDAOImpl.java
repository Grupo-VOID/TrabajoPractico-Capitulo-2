package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;

public class PromocionesDAOImpl implements PromocionesDAO {

	public List<Promocion> findAll() {
		try {
			String sql = "SELECT promociones.*, group_concat(ap.id_atraccion) AS lista_atracciones\r\n"
					+ "FROM promociones\r\n"
					+ "JOIN atracciones_promociones ap ON ap.id_promocion = promociones.id_promocion\r\n"
					+ "GROUP BY promociones.id_promocion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> listaPromociones = new LinkedList<Promocion>();

			while (resultados.next()) {
				listaPromociones.add(toPromocion(resultados));
			}
			return listaPromociones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Promocion buscarPorNombre(String nombre) {
		try {
			String sql = "SELECT * FROM promociones WHERE nombre_promocion = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;

			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}
			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Promocion buscarPorId(int id) {
		try {
			String sql = "SELECT * FROM promociones WHERE id_promocion = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;

			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}
			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promocion toPromocion(ResultSet resultados) throws SQLException {
		Promocion promocion = null;
		AtraccionesDAO atraccionesDAO = DAOFactory.getAtraccionesDAO();

		int tipoPromocion = resultados.getInt("id_tipo_promocion");
		String tematica = resultados.getString("nombre_promocion");
		String atraccionesIncluidas = resultados.getString("lista_atracciones");
		int parametro = resultados.getInt("parametro");

		String[] atr = atraccionesIncluidas.split(" ");
		Integer[] atracciones = Arrays.copyOf(atr, atr.length, Integer[].class);

		Atraccion atraccion1 = atraccionesDAO.buscarPorId(atracciones[0]);
		Atraccion atraccion2 = atraccionesDAO.buscarPorId(atracciones[1]);

		switch (tipoPromocion) {
		case 1:
			promocion = new PromocionPorcentual(tematica, atraccion1, atraccion2, parametro);
			break;
		case 2:
			promocion = new PromocionAbsoluta(tematica, atraccion1, atraccion2, parametro);
			break;
		case 3:
			Atraccion atraccionGratis = atraccionesDAO.buscarPorId(parametro);
			promocion = new PromocionAxB(tematica, atraccionesDAO.buscarPorId(atracciones[1]),
					atraccionesDAO.buscarPorId(atracciones[2]), atraccionGratis);
			break;
		}
		return promocion;
	}
}