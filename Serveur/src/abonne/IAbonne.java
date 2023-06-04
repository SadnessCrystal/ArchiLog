package abonne;

import java.time.LocalDate;

public interface IAbonne {
	int getId();
	String getNom();
	String getPrenom();
	LocalDate getDateNaissance();
	int getAge();
}
