package document;

public interface Document {
	int numero();

	// return null si pas emprunt� ou pas r�serv�
	Abonne empruntePar(); // Abonn� qui a emprunt� ce document

	Abonne reservePar(); // Abonn� qui a r�serv� ce document
	// precondition ni r�serv� ni emprunt�

	void reservation(Abonne ab);

	// precondition libre ou r�serv� par l�abonn� qui vient emprunter
	void emprunt(Abonne ab);

	// retour d�un document ou annulation d�une r�servation
	void retour();
}