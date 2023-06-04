package main;

import java.awt.im.InputMethodHighlight;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import bdd.ConnexionBDD;
import bdd.IConnexionBDD;
import bdd.IInteractionBDD;
import bdd.InteractionBDD;
import document.Document;
import ihm.IHM;
import ihm.IIHM;

public class Appli {
	public static void main(String[] main) {
		final int PORT = 1_002;
		
		IConnexionBDD cx = new ConnexionBDD();

		try {
			Connection c = cx.connexion(PORT);
			
			IInteractionBDD iBdd = new InteractionBDD();
			
			Map<Integer, Document> documents;
		
			documents = iBdd.getDocuments(c);
			
			IIHM ihm = new IHM();
			
			while (true) {
				int numeroDoc = ihm.getNumDocument(documents.keySet());
				boolean estEndomage = ihm.demandeEtatDocument();
				Document d = documents.get(numeroDoc);
				d.retour();
				iBdd.rendre(c, d);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
