package model;

import dao.AtraccionDAO;
import dao.DAOFactory;
import model.Atraccion;

public class Atraccion implements Adquirible {

	private final String NOMBRE;
	private final String TEMATICA;
	private final double COSTO;
	private final double DURACION;
	private final int CUPO_MAXIMO;
	private int lugaresOcupados = 0;

	public Atraccion(String nombre, String tematica, double costo, double duracion, int cupoMaximo) {
		this.NOMBRE = nombre;
		this.TEMATICA = tematica;
		this.COSTO = costo;
		this.DURACION = duracion;
		this.CUPO_MAXIMO = cupoMaximo;
	}

	public double getCosto() {
		return COSTO;
	}

	public double getTiempo() {
		return DURACION;
	}

	public String getTematica() {
		return TEMATICA;
	}

	public int getCupoActual() {
		return lugaresOcupados;
	}

	public int getCupoMaximo() {
		return CUPO_MAXIMO;
	}

	public String getNombre() {
		return NOMBRE;
	}

	public boolean estaDisponible() {
		return lugaresOcupados < CUPO_MAXIMO;
	}

	public void comprar() {
		lugaresOcupados++;
		
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionesDAO();
		atraccionDAO.updateCupoActual(this);
	}

	public Atraccion[] atraccionesIncluidas() {
		Atraccion[] nombre = { this };
		return nombre;
	}

	public boolean esPromocion() {
		return false;
	}

	@Override
	public String toString() {
		return String.format("Atraccion: " + this.NOMBRE 
				+ ". Tematica: " + this.getTematica()
				+ "\n Costo Total= $" + this.getCosto()
				+ "\n Duracion Total= " + this.getTiempo() + " horas");
	}
}