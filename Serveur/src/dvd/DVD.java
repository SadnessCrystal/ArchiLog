package dvd;

import abonne.IAbonne;
import document.Document;

public class DVD extends Document implements IDVD {
	private boolean interditMoins16Ans;
	private IVerificationAge va;
	
	public DVD(String titre, boolean autoriseMoins16Ans, IVerificationAge va) {
		super(titre);
		this.interditMoins16Ans = autoriseMoins16Ans;
		this.va = va;
	}
	
	@Override
	public boolean getInterditMoins16Ans() {
		return this.interditMoins16Ans;
	}
	
	@Override
	public boolean empruntable(IAbonne ab) {
		return super.empruntable(ab) && va.verificationPlus16Ans(this, ab);
	}
	
	@Override
	public boolean reservable(IAbonne ab) {
		return reservable(ab) && va.verificationPlus16Ans(this, ab);
	}
}
