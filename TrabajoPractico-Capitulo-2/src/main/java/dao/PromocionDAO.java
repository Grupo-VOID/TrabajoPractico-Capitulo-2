package dao;

import model.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {
	public Promocion buscarPorNombre(String nombre);
	
	public Promocion buscarPorId(int id);
	
}