package dao;

import java.util.List;

import model.Adquirible;
import model.Itinerario;
import model.Usuario;

public interface ItinerarioDAO  extends GenericDAO<Itinerario> {
		public List<Itinerario> findAll();
		public int insertPromocion(Usuario usuario, Adquirible nuevaAtraccion);
		public int insertAtraccion(Usuario usuario, Adquirible atraccion);
}
