package document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import abonne.IAbonne;
import banissement.IBanissement;
import enums.EtatDocument;
import exceptions.LivreIndisponibleException;

public abstract class Abstract_Document implements Document {
	private static int idCompteur = 1;

	private int id;
	private String titre;
	private EtatDocument etat;
	private IAbonne abonne;
	
	@SuppressWarnings("unused")
	private LocalDateTime dateReservation; // Pi�ge � con : penser � supprimer si 2h
	private LocalDate dateEmprunt;

	public Abstract_Document(String titre) {
		this.id = Abstract_Document.idCompteur++;
		this.titre = titre;
		this.etat = EtatDocument.DISPONIBLE;
	}

	public String getTitre() {
		return titre;
	}
	
	/*
	
	public boolean estEmprunte() {
		return this.dateEmprunt != null;
	}
	
	public boolean estReserve() {
		return this.dateReservation != null;
	}
	
	*/

	public EtatDocument getEtat() {
		return etat;
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
			throw new LivreIndisponibleException("Livre d�j� r�serv�/emprunt� par " + ab.getNom());
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
			this.dateEmprunt = LocalDate.now();
		} else {
			throw new LivreIndisponibleException("Livre d�j� r�serv�/emprunt� par " + ab.getNom());
		}
	}

	@Override
	public void retour() {
		this.etat = EtatDocument.DISPONIBLE;
		this.dateEmprunt = null;
		this.abonne = null;
	}
}
