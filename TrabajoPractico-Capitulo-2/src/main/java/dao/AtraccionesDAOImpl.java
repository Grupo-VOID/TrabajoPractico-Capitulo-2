package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;

public class AtraccionesDAOImpl implements AtraccionesDAO {

	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM ATRACCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> listaAtracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				String nombre = resultados.getString("nombre");
				String tematica = resultados.getString("tipo");
				double monedas = resultados.getInt("costo");
				double tiempo = resultados.getInt("tiempo");
				int cupo = resultados.getInt("cupo");
				Atraccion atraccion = new Atraccion(nombre, tematica, monedas, tiempo, cupo);
				listaAtracciones.add(atraccion);
			}
			return listaAtracciones;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}