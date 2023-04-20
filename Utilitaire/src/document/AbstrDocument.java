package document;

import java.time.LocalDateTime;

public class AbstrDocument implements Document {
	private static  int ID_COMPETUR = 1;
	
	private int id;
	private String titre;
	private EtatDocument etat;
	private Abonne abonne;
	private LocalDateTime dateReservation; // Piège à con
	
	public AbstrDocument(String titre) {
		this.id = AbstrDocument.ID_COMPETUR++;
		this.titre = titre;
		this.etat = EtatDocument.DISPONIBLE;
	}

	public String getTitre() {
		return titre;
	}

	public EtatDocument getEtat() {
		return etat;
	}

	@Override
	public int numero() {
		return this.id;
	}

	@Override
	public Abonne empruntePar() {
		if (etat == EtatDocument.EMPRUNTE)
			return this.abonne;
		else
			return null;
	}

	@Override
	public Abonne reservePar() {
		if (etat == EtatDocument.RESERVE)
			return this.abonne;
		else
			return null;
	}
	
	public boolean reservable(Abonne ab) {
		return this.etat == EtatDocument.DISPONIBLE;
	}

	@Override
	public void reservation(Abonne ab) {
		if (reservable(ab)) {
			this.abonne = ab;
			this.dateReservation = LocalDateTime.now();
			this.etat = EtatDocument.RESERVE;
		}
		else {
			throw new LivreIndisponible("Livre déjà réservé/emprunté par " + ab.getNom());
		}
	}
	
	public boolean empruntable(Abonne ab) {
		return this.etat== EtatDocument.DISPONIBLE || (this.etat==EtatDocument.RESERVE && ab==this.abonne);
	}

	@Override
	public void emprunt(Abonne ab) {
		if (empruntable(ab)) {
			this.etat = EtatDocument.EMPRUNTE;
			this.abonne = ab;
		}
		else {
			throw new LivreIndisponible("Livre déjà réservé/emprunté par " + ab.getNom());
		}
	}

	@Override
	public void retour() {
		this.abonne = null;
		this.etat = EtatDocument.DISPONIBLE;
	}
}
