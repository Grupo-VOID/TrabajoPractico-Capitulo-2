package model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.PromocionDAO;
import dao.UsuarioDAO;

public class App {

	public static void main(String[] args) throws SQLException, IOException {
		AtraccionDAO atraccionesDAO = DAOFactory.getAtraccionesDAO();
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		PromocionDAO promocionesDAO = DAOFactory.getPromocionesDAO();

		ParqueAtracciones parque = new ParqueAtracciones();
		parque.agregarUsuarios(usuarioDAO.findAll());
		parque.agregarAtracciones(new LinkedList<Adquirible>(atraccionesDAO.findAll()));
		parque.agregarPromociones(new LinkedList<Adquirible>(promocionesDAO.findAll()));

		parque.cargarCatalogo();

		UI.eleccionDeMenu(parque); 
	}

}