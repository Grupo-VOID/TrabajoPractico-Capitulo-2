package model;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public abstract class UI {

	public static void compraPorUsuario(ParqueAtracciones parque) throws IOException {
		Scanner sc = new Scanner(System.in);
		try {
			sistemaDeCompra(parque, sc);
		} catch (NumberFormatException e) {
			System.out.println("Debe ingresar un numero");
			compraPorUsuario(parque);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Seleccionar una opcion valida.");
			compraPorUsuario(parque);
		}
	}

	private static void sistemaDeCompra(ParqueAtracciones parque, Scanner sc) throws IOException {
		int idUsuario;
		System.out.println("::Bienvenido al Parque VOID::\n");
		do {
			System.out.println("::Sistema de compra::\n" + "Para obtener sugerencias seleccionar un usuario:");
			parque.mostrarUsuarios();
			idUsuario = Integer.valueOf(sc.nextLine());
			idUsuario--;
			if (idUsuario < parque.getUsuarios().size()) {
				Usuario usuarioTemp = parque.getUsuarios().get(idUsuario);
				System.out.println("Sugerencias para: " + usuarioTemp.getNombre() + "\n_Tematica favorita: "
						+ usuarioTemp.getTematica() + "\n_Dinero disponible: $" + usuarioTemp.getMonedasDisponibles()
						+ "\n_Tiempo disponible: " + usuarioTemp.getTiempoDisponible() + " horas\n");
				Collections.sort(parque.getCatalogo(), new Sugerencia(usuarioTemp));
				mostrarSugerencia(parque, usuarioTemp);
				System.out.println();
				idUsuario = 0;
			} else {
				if (idUsuario != parque.getUsuarios().size()) {
					System.out.println("Seleccionar una opcion valida.");
					idUsuario = 0;
				}
			}
		} while (idUsuario != parque.getUsuarios().size());
		System.out.println("\n\nMuchas gracias por utilizar nuestro programa.");
	}

	@SuppressWarnings("resource")
	public static void mostrarSugerencia(ParqueAtracciones parque, Usuario persona) {
		Scanner sc = new Scanner(System.in);
		for (Adquirible lista : parque.getCatalogo()) {
			if (Sugerencia.validarSugerencia(persona, lista)) {
				String op;
				System.out.println(lista);
				do {
					System.out.println("Desea comprar la sugerencia? Presione S para"
							+ " Si, N para No o X para finalizar la compra");
					op = sc.nextLine().toUpperCase();
					System.out.println(op);
					switch (op) {
					case "S":
						persona.aceptarSugerencia(lista);
						op = "N";
						break;
					case "N":
						op = "N";
						break;
					case "X":
						System.out.println("\nCompra finalizada\n**Itinerario del usuario " + persona.getNombre() + "**\n");
						persona.itinerarioUsuario.mostrarItinerario();
						System.out.println("\nPresione ENTER para continuar...");
						sc.nextLine();
						return;
					default:
						System.out.println("Introdujo una letra incorrecta");
						break;
					}
				} while (op != "N");
			}
		}
		System.out.println("\nCompra finalizada\n**Itinerario del usuario " + persona.getNombre() + "**\n");
		persona.itinerarioUsuario.mostrarItinerario();
		System.out.println("\nPresione ENTER para continuar...");
		sc.nextLine();
	}
}