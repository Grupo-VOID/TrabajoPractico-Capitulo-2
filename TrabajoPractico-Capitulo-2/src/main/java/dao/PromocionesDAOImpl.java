package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
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

			Promocion promocion;

			List<Promocion> listaPromociones = new LinkedList<Promocion>();

			while (resultados.next()) {
				int tipoPromocion = resultados.getInt("id_tipo_promocion");
				String tematica = resultados.getString("nombre_promocion");
				String atraccionesIncluidas = resultados.getString("lista_atracciones");
				int parametro = resultados.getInt("parametro");

				String[] atr = atraccionesIncluidas.split(" ");

				switch (tipoPromocion) {
				case 1:
					promocion = new PromocionPorcentual(tematica, atraccion1, atraccion2, parametro);
					break;
				case 2:
					promocion = new PromocionAbsoluta(tematica, atraccion1, atraccion2, parametro);
					break;
				case 3:
					promocion = new PromocionAxB(tematica, atraccion1, atraccion2, parametro);
					break;
				}
				listaPromociones.add(promocion);

			}
			return listaPromociones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Promocion buscarPorNombre(String nombre) throws SQLException {
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
	}

	public Promocion buscarPorId(int id) throws SQLException {
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
	}

	public int countAll() throws SQLException {
		String sql = "SELECT count(*) AS 'total' FROM promociones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("total");

		return total;
	}

	private Promocion toPromocion(ResultSet resultados) throws SQLException {
		return new Promocion(resultados.getInt("id_promocion"), resultados.getString("nombre_promocion"),
				resultados.getInt("id_tipo_promocion"), resultados.getInt("parametro"));
	}
}