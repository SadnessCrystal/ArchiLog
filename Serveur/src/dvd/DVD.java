package dvd;

import java.time.LocalDate;
import java.time.LocalDateTime;

import abonne.IAbonne;
import document.Abstract_Document;

public class DVD extends Abstract_Document implements IDVD {
	private boolean adulte;
	private IVerificationAge va;
	
	public DVD(int id, String titre, boolean estPourAdulte, IVerificationAge va) {
		super(id, titre);
		this.adulte = estPourAdulte;
		this.va = va;
	}
	
	public DVD(int id, String titre, LocalDateTime dateReservation, LocalDate dateEmprunt, IAbonne abonne, boolean estPourAdulte, IVerificationAge va) {
		super(id, titre, dateReservation, dateEmprunt, abonne);
		this.adulte = estPourAdulte;
		this.va = va;
	}
	
	@Override
	public boolean estPourAdulte() {
		return this.adulte;
	}
	
	@Override
	public boolean empruntable(IAbonne ab) {
		return super.empruntable(ab) && va.verificationAdulte(this, ab);
	}
	
	@Override
	public boolean reservable(IAbonne ab) {
		return super.reservable(ab) && va.verificationAdulte(this, ab);
	}
}
