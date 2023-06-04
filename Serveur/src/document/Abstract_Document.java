package document;

import java.time.LocalDate;
import java.time.LocalDateTime;

import abonne.IAbonne;
import exceptions.LivreIndisponibleException;

public abstract class Abstract_Document implements Document {
	private int id;
	private String titre;
	private EtatDocument etat;
	private IAbonne abonne;
	private LocalDateTime dateReservation; 	// Piège à con : penser à supprimer si 2h
	private LocalDate dateEmprunt;			// Penser à supprimer

	public Abstract_Document(int id, String titre) {
		this(id, titre, null, null, null);
	}
	
	public Abstract_Document(int id, String titre, LocalDateTime dateReservation, LocalDate dateEmprunt, IAbonne ab) {
		assert(dateReservation == null || dateEmprunt == null);
		
		this.id = id;
		this.titre = titre;
		
		if (dateReservation != null)
			this.etat = EtatDocument.RESERVE;
		else if (dateEmprunt != null)
			this.etat = EtatDocument.EMPRUNTE;
		else
			this.etat = EtatDocument.DISPONIBLE;
		
		this.abonne = ab;
		
	}

	public String getTitre() {
		return titre;
	}
	
	public boolean estEmprunte() {
		return this.etat == EtatDocument.EMPRUNTE;
	}
	
	public boolean estReserve() {
		return this.etat == EtatDocument.RESERVE;
	}
	
	public boolean estDisponible() {
		return this.etat == EtatDocument.DISPONIBLE;
	}

	@Override
	public int numero() {
		return this.id;
	}

	@Override
	public IAbonne empruntePar() {
		if (etat == EtatDocument.EMPRUNTE)
			return this.abonne;
		else
			return null;
	}

	@Override
	public IAbonne reservePar() {
		if (etat == EtatDocument.RESERVE)
			return this.abonne;
		else
			return null;
	}

	public boolean reservable(IAbonne ab) {
		return this.etat == EtatDocument.DISPONIBLE;
	}

	@Override
	public void reservation(IAbonne ab) {
		if (reservable(ab)) {
			this.abonne = ab;
			this.dateReservation = LocalDateTime.now();
			this.etat = EtatDocument.RESERVE;
		} else {
			throw new LivreIndisponibleException("Livre déjà réservé/emprunté par " + ab.getNom());
		}
	}

	public boolean empruntable(IAbonne ab) {
		return this.etat == EtatDocument.DISPONIBLE || (this.etat == EtatDocument.RESERVE && ab == this.abonne);
	}

	@Override
	public void emprunt(IAbonne ab) {
		if (empruntable(ab)) {
			this.etat = EtatDocument.EMPRUNTE;
			this.abonne = ab;
			this.dateEmprunt = LocalDate.now(); //Date.valueOf(LocalDate.now());
		} else {
			throw new LivreIndisponibleException("Livre déjà réservé/emprunté par " + ab.getNom());
		}
	}

	@Override
	public void retour() {
		this.etat = EtatDocument.DISPONIBLE;
		this.dateEmprunt = null;
		this.abonne = null;
	}
}
