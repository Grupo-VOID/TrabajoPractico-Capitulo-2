package model;

public abstract class Promocion implements Adquirible {

	protected final String TEMATICA;
	protected double costoTotal = 0;
	protected double duracionTotal = 0;

	public Promocion(String tematica) {
		this.TEMATICA = tematica;
	}

	public abstract double getCosto();

	public abstract double getTiempo();

	public abstract boolean estaDisponible();

	public abstract Atraccion[] atraccionesIncluidas();

	public String getNombre() {
		return "Pack " + this.TEMATICA;
	}

	public String getTematica() {
		return TEMATICA;
	}

	public boolean esPromocion() {
		return true;
	}
}