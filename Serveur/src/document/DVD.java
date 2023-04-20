package document;

import java.time.LocalDate;
import java.time.Period;

import abonne.Abonne;

public class DVD extends AbstrDocument {
	private boolean autoriseMoins16Ans;
	
	public DVD(String titre, boolean autoriseMoins16Ans) {
		super(titre);
		this.autoriseMoins16Ans = autoriseMoins16Ans;
	}
	
	private boolean verificationPlus16Ans(Abonne ab) {
		LocalDate aujourdhui = LocalDate.now();
		int age = Period.between(aujourdhui, ab.getDateNaissance()).getYears();
		
		return !this.autoriseMoins16Ans || age >= 16;
	}
	
	@Override
	public boolean empruntable(Abonne ab) {
		return super.empruntable(ab) && verificationPlus16Ans(ab);
	}
	
	@Override
	public boolean reservable(Abonne ab) {
		return reservable(ab) && verificationPlus16Ans(ab);
	}
}
