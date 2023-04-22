package abonne;

import java.time.LocalDate;
import java.time.Period;

public class Abonne implements IAbonne {
	private static int idCompteur = 1;
	private int id;
	private String nom;
	private LocalDate dateNaissance;

	public Abonne(String nom, LocalDate dateNaissance) {
		this.id = Abonne.idCompteur++;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
	}
	
	public int getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	
	public int getAge() {
		return Period.between(this.getDateNaissance(), LocalDate.now()).getYears();
	}

}
