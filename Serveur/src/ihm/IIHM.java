package ihm;

import java.util.Set;

public interface IIHM {
	 int getNumAbonne(Set<Integer> listeNumeroAbonnes);
	 
	 int getNumDocument(Set<Integer> listeNumeroDocuments);
	 
	 boolean demandeEtatDocument();
}