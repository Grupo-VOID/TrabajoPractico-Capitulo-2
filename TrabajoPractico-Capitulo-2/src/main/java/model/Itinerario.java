package model;

import java.util.ArrayList;

import dao.DAOFactory;
import dao.ItinerarioDAO;

public class Itinerario {
	private ArrayList<Adquirible> listaAtracciones = new ArrayList<Adquirible>();
	private double tiempoTotal = 0;
	private double costoMonedas = 0;

	public void agregarAdquirible(Adquirible nuevaAtraccion, Usuario usuario) {
		listaAtracciones.add(nuevaAtraccion);
		tiempoTotal += nuevaAtraccion.getTiempo();
		costoMonedas += nuevaAtraccion.getCosto();		

		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		if(nuevaAtraccion.esPromocion()) {
			itinerarioDAO.insertPromocion(usuario, nuevaAtraccion);
		} else {
			itinerarioDAO.insertAtraccion(usuario, nuevaAtraccion);
		}
	}

	public ArrayList<Adquirible> getListaAtracciones() {
		return listaAtracciones;
	}

	public double getCostoMonedas() {
		return costoMonedas;
	}

	public double getTiempoTotal() {
		return tiempoTotal;
	}

	public void mostrarItinerario() {
		if (listaAtracciones.isEmpty())
			System.out.println("El usuario no ha realizado compras");
		else {
			System.out.println("Las atracciones compradas son:");
			for (Adquirible lista : listaAtracciones) {
				System.out.println("  " + lista.getNombre());
			}
			System.out.println("El costo total es: $" + this.costoMonedas);
			System.out.println("El tiempo total es: " + this.tiempoTotal + " horas");
		}
	}
}