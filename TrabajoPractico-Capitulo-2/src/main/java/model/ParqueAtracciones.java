package model;

import java.util.LinkedList;
import java.util.List;

public class ParqueAtracciones {

	private List<Adquirible> atracciones, promociones;
	private LinkedList<Adquirible> catalogo = new LinkedList<Adquirible>();
	private List<Usuario> usuarios;

	public void agregarAtracciones(List<Adquirible> atracciones) {
		this.atracciones = atracciones;
	}

	public void agregarPromociones(List<Adquirible> promociones) {
		this.promociones = promociones;
	}

	public void agregarUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void mostrarUsuarios() {
		int i;
		for (i = 0; i < this.usuarios.size(); i++) {
			System.out.print(i + 1 + ". " + this.usuarios.get(i).getNombre() + "\n");
		}
		System.out.println("\n");
		System.out.print(i + 1 + ". " + "Finalizar programa\n");
	}

	public void cargarCatalogo() {
		this.catalogo.addAll(this.promociones);
		this.catalogo.addAll(this.atracciones);
	}

	public Adquirible obtenerAtraccionPorNombre(String nombre) {
		for (Adquirible atraccion : atracciones) {
			if (atraccion.getNombre().equals(nombre)) {
				return atraccion;
			}
		}
		return null;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public List<Adquirible> getAtracciones() {
		return this.atracciones;
	}

	public List<Adquirible> getPromociones() {
		return this.promociones;
	}

	public List<Adquirible> getCatalogo() {
		return catalogo;
	}
}