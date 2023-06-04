package ihm;

import java.util.Scanner;
import java.util.Set;
public class IHM implements IIHM {
	
	@Override
	public int getNumAbonne(Set<Integer> listeNumeroAbonnes) {
		return this.demandeNumero(listeNumeroAbonnes, "Saississez le num�ro d'ad�rent : ");
	}
	
	@Override
	public int getNumDocument(Set<Integer> listeNumeroDocuments) {
		return this.demandeNumero(listeNumeroDocuments, "Saississez le num�ro du document : ");
	}

	private int demandeNumero(Set<Integer> listeNumeros, String message) {
		Scanner sc = new Scanner(System.in);
		int numeroSaisi;

		while (true) {
			try {
				while (true) {
					System.out.println(message);
					
					numeroSaisi = sc.nextInt();
					
					if (listeNumeros.contains(numeroSaisi))
						return numeroSaisi;
					else 
						System.out.println("Ce num�ro n'existe pas.");
				}

			} catch (Exception e) {
				System.out.println("Erreur : Entr�e invalide. Veuillez entrer un entier." + System.lineSeparator());
			}
		}
	}

	@Override
	public boolean demandeEtatDocument() {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("Ce document a t'il �t� endommag� ? (O/N)");
			
			String c = sc.next();
			
			if (c.length() > 1)
				System.out.println("Une seule lettre !");
			else if (c == "o" || c == "O")
				return true;
			else if (c == "n" || c == "N")
				return false;
			else
				System.out.println("Caract�re incorrect.");
		}
	}
}
