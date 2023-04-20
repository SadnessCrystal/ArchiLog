package document;

import abonne.Abonne;

public class DVD extends AbstrDocument {
	private boolean interditMoins16Ans;
	
	public DVD(String titre, boolean autoriseMoins16Ans) {
		super(titre);
		this.interditMoins16Ans = autoriseMoins16Ans;
	}
	
	private boolean verificationPlus16Ans(Abonne ab) {
		
		return !this.interditMoins16Ans || 16 <= ab.getAge();
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
