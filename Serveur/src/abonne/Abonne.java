package abonne;

import java.time.LocalDate;
import java.time.Period;

import banissement.IBanissement;

public class Abonne implements IAbonne {
	private static int idCompteur = 1;
	private int id;
	private String nom;
	
	private LocalDate dateNaissance;
	private LocalDate dateBanissement;

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
	
	/*
	public boolean estBanni() {
		return this.dateBanissement != null;
	}
	
	public LocalDate getDateBannissement() {
		return this.dateBanissement;
	}
	
	public void bannir() {
		this.dateBanissement = LocalDate.now();
	}
	*/

}
