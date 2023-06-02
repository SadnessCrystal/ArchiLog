package banissement;

import java.time.LocalDate;
import java.time.Period;

import abonne.IAbonne;
import document.Document;

public class Banissement implements IBanissement {
	
	private static final int NB_JOURS_RETARD_DOCUMENT_BANISSEMENT = 14;
	
	private int nbJoursRetard(LocalDate dateEmprunt) {
		return Period.between(dateEmprunt, LocalDate.now()).getDays();
	}

	@Override
	public boolean verificationRetard(IAbonne ab, LocalDate dateEmprunt) {
		return nbJoursRetard(dateEmprunt) > NB_JOURS_RETARD_DOCUMENT_BANISSEMENT;
	}
}
