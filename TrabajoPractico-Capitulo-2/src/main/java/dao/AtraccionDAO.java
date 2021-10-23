package dao;

import java.sql.SQLException;
import java.util.List;

import model.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion> {
	
	public List<Atraccion> findAll();

	public int updateCupoActual(Atraccion atraccion) throws SQLException;

	public Atraccion buscarPorNombre(String nombre) throws SQLException;

	public Atraccion buscarPorId(int id) throws SQLException;
}