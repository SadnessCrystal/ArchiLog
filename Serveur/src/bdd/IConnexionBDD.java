package bdd;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnexionBDD {
	/**
	 * 
	 * @param serverPort Numéro de port du serveur
	 * @return Connexion à la BDD
	 * @throws SQLException
	 */
	Connection connexion(int serverPort) throws SQLException;
}