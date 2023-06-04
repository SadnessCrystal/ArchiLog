package document;

import abonne.IAbonne;

public interface Document {
	int numero();

	// return null si pas emprunt� ou pas r�serv�
	IAbonne empruntePar(); // Abonn� qui a emprunt� ce document

	IAbonne reservePar(); // Abonn� qui a r�serv� ce document
	
	// precondition ni r�serv� ni emprunt�
	void reservation(IAbonne ab);

	// precondition libre ou r�serv� par l�abonn� qui vient emprunter
	void emprunt(IAbonne ab);

	// retour d�un document ou annulation d�une r�servation
	void retour();
}