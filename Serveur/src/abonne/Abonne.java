package abonne;

import java.time.LocalDate;
import java.time.Period;

public class Abonne {
	private static int ID_COMPTEUR = 1;
	private int id;
	private String nom;
	private LocalDate dateNaissance;

	public Abonne(String nom, LocalDate dateNaissance) {
		this.id = Abonne.ID_COMPTEUR++;
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
		return Period.between(LocalDate.now(), this.getDateNaissance()).getYears();
	}

}
