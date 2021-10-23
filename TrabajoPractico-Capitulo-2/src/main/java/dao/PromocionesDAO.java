package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Promocion;

public interface PromocionesDAO extends GenericDAO<Promocion> {
	public Promocion buscarPorNombre(String nombre);
	
	public Promocion buscarPorId(int id);
	
}