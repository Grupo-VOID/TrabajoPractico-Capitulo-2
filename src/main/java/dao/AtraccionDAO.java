package dao;

import java.util.List;

import model.Adquirible;
import model.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion> {

	public List<Atraccion> findAll();

	public int updateCupoActual(Atraccion atraccion);

	public Atraccion buscarPorId(int id);

	public int encontrarIdAtraccion(Adquirible atraccion);
}