package bdd;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnexionBDD {
	/**
	 * 
	 * @param serverPort Num�ro de port du serveur
	 * @return Connexion � la BDD
	 * @throws SQLException
	 */
	Connection connexion(int serverPort) throws SQLException;
}