package model;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(COSTO, CUPO_MAXIMO, DURACION, NOMBRE, TEMATICA, lugaresOcupados);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return Double.doubleToLongBits(COSTO) == Double.doubleToLongBits(other.COSTO)
				&& CUPO_MAXIMO == other.CUPO_MAXIMO
				&& Double.doubleToLongBits(DURACION) == Double.doubleToLongBits(other.DURACION)
				&& Objects.equals(NOMBRE, other.NOMBRE) && Objects.equals(TEMATICA, other.TEMATICA)
				&& lugaresOcupados == other.lugaresOcupados;
	}
}