package abonne;

import java.time.LocalDate;

public interface IAbonne {
	int getId();
	String getNom();
	LocalDate getDateNaissance();
	int getAge();
}
