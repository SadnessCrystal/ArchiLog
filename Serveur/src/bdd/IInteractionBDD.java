package bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import abonne.IAbonne;
import document.Document;

public interface IInteractionBDD {
	
	/**
	 * 
	 * @param c Connexion à la BDD
	 * @return Liste des abonnées
	 * @throws SQLException
	 */
	IAbonne getAbonne(Connection c, int numAbonne) throws SQLException;
	
	Map<Integer, IAbonne> getAbonnes(Connection c) throws SQLException;
	
	Document getDocument(Connection c, int numDocument) throws SQLException;

	Map<Integer, Document> getDocuments(Connection c) throws SQLException;
	
	String getCatalogue(Connection c);

	boolean commit(Connection c, IAbonne ab, Document d) throws SQLException;
}