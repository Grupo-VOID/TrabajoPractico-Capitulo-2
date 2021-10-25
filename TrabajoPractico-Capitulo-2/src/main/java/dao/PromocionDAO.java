package dao;

import model.Adquirible;
import model.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {

	public Promocion buscarPorId(int id);

	public int encontrarIdPromocion(Adquirible promocion);
}