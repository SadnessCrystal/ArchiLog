package dvd;

import abonne.IAbonne;

public class VerificationAge implements IVerificationAge {
	
	private static final int AGE_LIMITE = 16;

	@Override
	public boolean verificationAdulte(IDVD dvd, IAbonne ab) {
		return !dvd.estPourAdulte() || AGE_LIMITE <= ab.getAge();
	}
}
