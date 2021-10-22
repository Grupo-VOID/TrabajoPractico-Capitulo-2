package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionProvider {

	private static String url = "jdbc:sqlite:./bd/parque_atracciones.db";
	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(url);
		}
		return connection;
	}
}

//	public static void main(String[] args) throws SQLException {
//		String url = "jdbc:sqlite:./bd/parque_atracciones.db";
//		Connection connection = DriverManager.getConnection(url);
//
//		String sql = "SELECT COUNT(1) AS TOTAL FROM USUARIOS";
//		PreparedStatement statement = connection.prepareStatement(sql);
//
//		ResultSet resultados = statement.executeQuery();
//		
//		resultados.next();
//		System.out.println(resultados.getInt("TOTAL"));
//
//		connection.close();
//	}
//}