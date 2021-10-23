package dao;

import java.sql.SQLException;

import model.Atraccion;

public interface AtraccionesDAO extends GenericDAO<Atraccion> {

	public int updateCupoActual(Atraccion atraccion) throws SQLException;

	public Atraccion buscarPorNombre(String nombre);

	public Atraccion buscarPorId(int id);
}