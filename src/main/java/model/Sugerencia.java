package model;

import java.util.Comparator;

public class Sugerencia implements Comparator<Adquirible> {
	private String atraccionFavorita;

	public Sugerencia(Usuario usuarioRef) {
		atraccionFavorita = usuarioRef.getTematica();
	}

	public int compare(Adquirible adquirible1, Adquirible adquirible2) {
		if (adquirible1.getTematica() == atraccionFavorita && adquirible2.getTematica() != atraccionFavorita) {
			return -1;
		} else if (adquirible1.getTematica() != atraccionFavorita && adquirible2.getTematica() == atraccionFavorita)
			return 1;
		if (adquirible1.esPromocion() && !adquirible2.esPromocion())
			return -1;
		else if (!adquirible1.esPromocion() && adquirible2.esPromocion())
			return 1;
		if (adquirible1.getCosto() > adquirible2.getCosto())
			return -1;
		else if (adquirible1.getCosto() < adquirible2.getCosto())
			return 1;
		return (int) (adquirible2.getTiempo() - adquirible1.getTiempo());
	}

	public static boolean validarSugerencia(Usuario persona, Adquirible sugerencia) {
		if (validarTiempoCosto(persona, sugerencia)) {
			if (sugerencia.esPromocion()) {
				for (Atraccion i : sugerencia.atraccionesIncluidas()) {
					if (Sugerencia.validarSugerencia(persona, i) == false)
						return false;
				}
				return true;
			} else {
				if (sugerencia.estaDisponible()) {
					if (validarTiempoCosto(persona, sugerencia)) {
						if (persona.getListaAtracciones().isEmpty()) {
							return true;
						} else {
							for (Atraccion i : sugerencia.atraccionesIncluidas()) {
								if (persona.getListaAtracciones().contains(i)) {
									return false;
								}
							};
							return true;
						}
					}
					return false;
				}
				return false;
			}
		}
		return false;
	}

	public static boolean validarTiempoCosto(Usuario persona, Adquirible sugerencia) {
		return (persona.getMonedasDisponibles() >= sugerencia.getCosto()
				&& persona.getTiempoDisponible() >= sugerencia.getTiempo());
	}
}