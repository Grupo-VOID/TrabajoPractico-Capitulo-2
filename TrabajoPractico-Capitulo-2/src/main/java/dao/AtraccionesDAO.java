package dao;

import model.Atraccion;

public interface AtraccionesDAO extends GenericDAO<Atraccion> {

	public int updateCupoActual(Atraccion atraccion);

	public Atraccion buscarPorNombre(String nombre);

	public Atraccion buscarPorId(int id);
}