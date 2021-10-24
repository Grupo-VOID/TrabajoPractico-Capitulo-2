package dao;

import java.sql.SQLException;
import java.util.List;

import model.Adquirible;
import model.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion> {
	
	public List<Atraccion> findAll();

	public int updateCupoActual(Atraccion atraccion);

	public Atraccion buscarPorNombre(String nombre);

	public Atraccion buscarPorId(int id);
	
	public int encontrarIdAtraccion(Adquirible atraccion);
}