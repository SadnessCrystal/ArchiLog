package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD implements IConnexionBDD {
	private static final String SERVER_IP_ADRESS = "localhost"; // Adresse IP du serveur UwAmp
	private static final String DATABASE_NAME = "world";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";
	
	@Override
	public Connection connexion(int serverPort) throws SQLException {
		final String url = "jdbc:mysql://" + SERVER_IP_ADRESS + ":" + serverPort + "/" + DATABASE_NAME;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(url, USERNAME, PASSWORD);
	}
}
