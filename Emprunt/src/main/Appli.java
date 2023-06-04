package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import abonne.IAbonne;
import bdd.ConnexionBDD;
import bdd.IConnexionBDD;
import bdd.IInteractionBDD;
import bdd.InteractionBDD;
import document.Document;
import exceptions.LivreIndisponibleException;
import ihm.IHM;
import ihm.IIHM;

public class Appli {
	public static void main(String[] main) {
		final int PORT = 1_001;

		IConnexionBDD cx = new ConnexionBDD();

		try {
			Connection c = cx.connexion(PORT);

			IInteractionBDD iBdd = new InteractionBDD();

			Map<Integer, IAbonne> abonnes = iBdd.getAbonnes(c);
			Map<Integer, Document> documents = iBdd.getDocuments(c);

			IIHM ihm = new IHM();

			while (true) {
				int numeroAbonne = ihm.getNumAbonne(abonnes.keySet());
				int numeroDoc = ihm.getNumDocument(documents.keySet());

				IAbonne a = abonnes.get(numeroAbonne);
				Document d = documents.get(numeroDoc);

				try {
					d.emprunt(a);
					iBdd.emprunter(c, a, d);
				} catch (LivreIndisponibleException e) {
					System.out.println("Emprunt impossible.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
