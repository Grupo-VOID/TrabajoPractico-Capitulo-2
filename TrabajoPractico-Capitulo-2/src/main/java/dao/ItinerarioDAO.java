package dao;

import java.util.ArrayList;
import java.util.List;

import model.Adquirible;
import model.Atraccion;
import model.Itinerario;
import model.Usuario;

public interface ItinerarioDAO extends GenericDAO<Itinerario> {
	public int insertPromocion(Usuario usuario, Adquirible nuevaAtraccion);

	public int insertAtraccion(Usuario usuario, Adquirible atraccion);

	public List<Atraccion> atraccionesUsuario(int id);

	List<Adquirible> obtenerAdquiribles(int idUsuario);
}
