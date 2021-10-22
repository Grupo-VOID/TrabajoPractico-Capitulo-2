package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;

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
				String tipoPromocion = resultados.getString("id_tipo_promocion");
				String tematica = resultados.getString("nombre_promocion");
				String atraccionesIncluidas = resultados.getString("lista_atracciones");
				int descuento = resultados.getInt("descuento");
				
				String [] atr = atraccionesIncluidas.split(" ");
				
				switch(tipoPromocion) {
				case "1":
					promocion = new PromocionPorcentual(tematica, atraccion1, atraccion2, descuento);
					break;
				case "2":
					promocion = new PromocionAbsoluta(tematica, atraccion1, atraccion2, descuento);
					break;
				case "3":
					promocion = new PromocionAxB(tematica, atraccion1, atraccion2, descuento);
					break;
					}
				listaPromociones.add(promocion);
			
			}
			return listaPromociones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}