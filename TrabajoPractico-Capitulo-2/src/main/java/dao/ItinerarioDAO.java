package dao;

import java.util.List;

import model.Atraccion;
import model.Itinerario;
import model.Promocion;
import model.Usuario;

public interface ItinerarioDAO  extends GenericDAO<Itinerario> {
		public List<Itinerario> findAll();
		public int insertPromocion(Usuario usuario, Promocion promocion);
		public int insertAtraccion(Usuario usuario, Atraccion atraccion);
}
