package model;

import dao.AtraccionDAO;
import dao.DAOFactory;

public class PromocionAbsoluta extends Promocion {

	private double descuento = 0;
	private Atraccion atraccionUno;
	private Atraccion atraccionDos;

	public PromocionAbsoluta(String tematica, Atraccion atraccion1, Atraccion atraccion2, double descuento) {
		super(tematica);
		this.atraccionUno = atraccion1;
		this.atraccionDos = atraccion2;
		this.descuento = descuento;
	}

	@Override
	public double getCosto() {
		this.costoTotal = this.atraccionUno.getCosto() + this.atraccionDos.getCosto() - descuento;
		return this.costoTotal;
	}

	@Override
	public double getTiempo() {
		this.duracionTotal = atraccionUno.getTiempo() + atraccionDos.getTiempo();
		return duracionTotal;
	}

	@Override
	public boolean estaDisponible() {
		return (this.atraccionUno.estaDisponible() && this.atraccionDos.estaDisponible());
	}

	@Override
	public Atraccion[] atraccionesIncluidas() {
		Atraccion[] atracciones = { this.atraccionUno, this.atraccionDos };
		return atracciones;
	}

	public void comprar() {
		this.atraccionUno.comprar();
		this.atraccionDos.comprar();
		
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionesDAO();
		atraccionDAO.updateCupoActual(atraccionUno);
		atraccionDAO.updateCupoActual(atraccionDos);
	}

	@Override
	public String toString() {
		return (this.getNombre()+": Si compra la atraccion " + this.atraccionUno.getNombre()
				+ " y la atraccion " + this.atraccionDos.getNombre()
				+ ", obtiene un descuento de $" + this.descuento + " sobre el costo total."
				+ "\n Tematica: " + this.getTematica()
				+ "\n Costo Total= $" + this.getCosto()
				+ "\n Duracion Total= " + this.getTiempo() + " horas");
	}
}