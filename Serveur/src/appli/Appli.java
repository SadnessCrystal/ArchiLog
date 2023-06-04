package appli;

import java.net.Socket;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import abonne.IAbonne;
import bdd.ConnexionBDD;
import bdd.IConnexionBDD;
import bdd.IInteractionBDD;
import bdd.InteractionBDD;
import document.Document;

public class Appli {

	public static void main(String[] args) {
		Map<Integer, IAbonne> abonnes = new HashMap<>();
		Map<Integer, Document> documents = new HashMap<>();

		try {
			IConnexionBDD c = new ConnexionBDD();
			Connection connection =  c.connexion(3307);
			//Socket socket = new Socket(serverIpAddress, serverPort);

			IInteractionBDD ibdd= new InteractionBDD();
			Map<Integer, Document> m = ibdd.getDocuments(connection);
			
			for (Document d : m.values()) {
				System.out.println(d.numero() + " ");
			}

			//socket.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		} 

	}
}
