package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import dao.AtraccionesDAO;
import dao.DAOFactory;
import dao.MissingDataException;
import dao.UserDAO;
import jdbc.ConnectionProvider;

public class App {

//	public static void main(String[] args) throws IOException {

//			ParqueAtracciones parque = new ParqueAtracciones();
//			parque.agregarUsuarios(LectorArchivos.GenerarUsuarios("entrada/usuarios.txt"));
//			parque.agregarAtracciones(LectorArchivos.GenerarAtracciones("entrada/atracciones.txt"));
//			parque.agregarPromociones(LectorArchivos.GenerarPromociones(parque, "entrada/promociones.txt"));
//			parque.cargarCatalogo();
//
//			UI.eleccionDeMenu(parque); // Método para elegir modo de compra
//		}
//	}
	public static void main(String[] args) throws SQLException {
		AtraccionesDAO atraccionesDAO = DAOFactory.getAtraccionesDAO();
		System.out.println(atraccionesDAO.findAll());
}
//	
//	public static void main(String[] args) throws SQLException {
//	UserDAO userDAO = DAOFactory.getUserDAO();
//	System.out.println(userDAO.findAll());
//}
}