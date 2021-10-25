package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Adquirible;
import model.Atraccion;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;

public class PromocionDAOImpl implements PromocionDAO {

	public List<Promocion> findAll() {
		try {
			String sql = "SELECT promociones.*, group_concat(ap.id_atraccion,' ') AS lista_atracciones\r\n"
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

	public int encontrarIdPromocion(Adquirible promocion) {
		try {
			String sql = "SELECT * FROM promociones WHERE nombre_promocion = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getTematica());
			ResultSet resultados = statement.executeQuery();

			int id = 0;

			if (resultados.next()) {
				id = resultados.getInt("id_promocion");
			}
			return id;
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
		AtraccionDAO atraccionesDAO = DAOFactory.getAtraccionesDAO();

		int tipoPromocion = resultados.getInt("id_tipo_promocion");
		String tematica = resultados.getString("nombre_promocion");
		String atraccionesIncluidas = resultados.getString("lista_atracciones");
		int parametro = resultados.getInt("parametro");

		String[] atr = atraccionesIncluidas.split(" ");

		Atraccion[] atracciones = new Atraccion[2];
		int contador = 0;

		for (String i : atr) {
			if (tipoPromocion == 3) {
				if (Integer.parseInt(i) != parametro) {
					atracciones[contador] = atraccionesDAO.buscarPorId(Integer.parseInt(i));
					contador++;
				}
			} else {
				atracciones[contador] = atraccionesDAO.buscarPorId(Integer.parseInt(i));
				contador++;
			}
		}
		switch (tipoPromocion) {
		case 1:
			promocion = new PromocionPorcentual(tematica, atracciones[0], atracciones[1], parametro);
			break;
		case 2:
			promocion = new PromocionAbsoluta(tematica, atracciones[0], atracciones[1], parametro);
			break;
		case 3:
			Atraccion atraccionGratis = atraccionesDAO.buscarPorId(parametro);
			promocion = new PromocionAxB(tematica, atracciones[0], atracciones[1], atraccionGratis);
			break;
		}
		return promocion;
	}
}