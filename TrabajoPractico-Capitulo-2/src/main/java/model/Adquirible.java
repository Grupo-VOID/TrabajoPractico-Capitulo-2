package model;

public interface Adquirible {

	public double getCosto();

	public double getTiempo();

	public String getTematica();

	public void comprar();

	public boolean esPromocion();

	public String getNombre();

	public boolean estaDisponible();

	public Atraccion[] atraccionesIncluidas();
}