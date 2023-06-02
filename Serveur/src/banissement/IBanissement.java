package banissement;

import java.time.LocalDate;

import abonne.IAbonne;

public interface IBanissement {
	boolean verificationRetard(IAbonne ab, LocalDate dateEmprunt);
}
