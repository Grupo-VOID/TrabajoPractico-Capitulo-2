package dao;

import java.sql.ResultSet;

import model.Promocion;

public interface PromocionesDAO extends GenericDAO<Promocion> {
	public Promocion buscarPorNombre(String nombre);
	
	public Promocion buscarPorId(int id);
	
	private Promocion toPromocion(ResultSet resultados);
}