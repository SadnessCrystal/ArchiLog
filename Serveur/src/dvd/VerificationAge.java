package dvd;

import abonne.IAbonne;

public class VerificationAge implements IVerificationAge {

	@Override
	public boolean verificationPlus16Ans(IDVD dvd, IAbonne ab) {
		return !dvd.getInterditMoins16Ans() || 16 <= ab.getAge();
	}
}
