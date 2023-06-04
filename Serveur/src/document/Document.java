package document;

import abonne.IAbonne;

public interface Document {
	int numero();

	// return null si pas emprunté ou pas réservé
	IAbonne empruntePar(); // Abonné qui a emprunté ce document

	IAbonne reservePar(); // Abonné qui a réservé ce document
	
	// precondition ni réservé ni emprunté
	void reservation(IAbonne ab);

	// precondition libre ou réservé par l’abonné qui vient emprunter
	void emprunt(IAbonne ab);

	// retour d’un document ou annulation d‘une réservation
	void retour();
}