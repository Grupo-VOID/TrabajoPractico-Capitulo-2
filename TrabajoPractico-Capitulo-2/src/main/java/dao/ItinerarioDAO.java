package dao;

import model.Adquirible;
import model.Itinerario;
import model.Usuario;

public interface ItinerarioDAO extends GenericDAO<Itinerario> {
	public int insertPromocion(Usuario usuario, Adquirible nuevaAtraccion);

	public int insertAtraccion(Usuario usuario, Adquirible atraccion);
}
