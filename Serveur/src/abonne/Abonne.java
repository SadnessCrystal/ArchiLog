package abonne;

import java.time.LocalDate;
import java.time.Period;

public class Abonne implements IAbonne {
	private int id;
	private String nom;
	private String prenom;

	private LocalDate dateNaissance;
	private LocalDate dateBanissement;

	public Abonne(int id, String nom, String prenom, LocalDate dateNaissance) {
		this(id, nom, prenom, dateNaissance, null);
	}

	public Abonne(int id, String nom, String prenom, LocalDate dateNaissance, LocalDate dateBanissement) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.dateBanissement = dateBanissement;
	}

	public int getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public int getAge() {
		return Period.between(this.getDateNaissance(), LocalDate.now()).getYears();
	}

	public boolean estBanni() {
		return this.dateBanissement != null;
	}

	public LocalDate getDateBannissement() {
		return this.dateBanissement;
	}

	public void bannir() {
		this.dateBanissement = LocalDate.now();
	}

}
