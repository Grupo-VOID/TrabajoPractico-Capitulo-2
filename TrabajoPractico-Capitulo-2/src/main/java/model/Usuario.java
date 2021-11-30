package model;

import java.util.ArrayList;

import dao.DAOFactory;
import dao.ItinerarioDAO;
import dao.UsuarioDAO;

public class Usuario {

	private final int ID;
	private final String NOMBRE;
	private final String TEMATICA_FAVORITA;
	private final double MONEDAS_INICIALES;
	private double monedasDisponibles;
	private final double TIEMPO_INICIAL;
	private double tiempoDisponible;
	protected Itinerario itinerarioUsuario;
	private ArrayList<Atraccion> listaAtracciones = new ArrayList<Atraccion>();

	public Usuario(int id, String nombre, String tematica, double monedas, double tiempo) {
		this.ID = id;
		this.NOMBRE = nombre;
		this.TEMATICA_FAVORITA = tematica;
		this.MONEDAS_INICIALES = monedas;
		this.monedasDisponibles = monedas;
		this.TIEMPO_INICIAL = tiempo;
		this.tiempoDisponible = TIEMPO_INICIAL;
		this.itinerarioUsuario = new Itinerario();
	}

	public String getNombre() {
		return NOMBRE;
	}

	public int getID() {
		return ID;
	}

	public String getTematica() {
		return TEMATICA_FAVORITA;
	}

	public double getMonedasDisponibles() {
		return monedasDisponibles;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public ArrayList<Atraccion> getListaAtracciones() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		listaAtracciones = (ArrayList<Atraccion>) itinerarioDAO.atraccionesUsuario(this.getID());

		return listaAtracciones;
	}

	public double monedasUtilizadas() {
		return this.MONEDAS_INICIALES - this.monedasDisponibles;
	}

	public void aceptarSugerencia(Adquirible sugerencia) {
		this.monedasDisponibles -= sugerencia.getCosto();
		this.tiempoDisponible -= sugerencia.getTiempo();
		this.itinerarioUsuario.agregarAdquirible(sugerencia, this);
//		for (Atraccion i : sugerencia.atraccionesIncluidas()) {
//			listaAtracciones.add(i);
//		}
		sugerencia.comprar();

		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		usuarioDAO.updateUsuario(this);
	}

	public double getMonedasIniciales() {
		return MONEDAS_INICIALES;
	}

	public double getTiempoInicial() {
		return TIEMPO_INICIAL;
	}

	@Override
	public String toString() {
		return this.NOMBRE + this.tiempoDisponible;
	}
}