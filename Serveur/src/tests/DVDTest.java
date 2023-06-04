package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import abonne.Abonne;
import abonne.IAbonne;
import dvd.DVD;
import dvd.IVerificationAge;
import dvd.VerificationAge;
import exceptions.LivreIndisponibleException;

public class DVDTest {
	private DVD dvdToutAge;
	private DVD dvdPlus16Ans;
	private static final String TITRE_TOUT_AGE = "Oui-Oui";
	private static final String TITRE_PLUS_16ANS = "Les plus belles miches de la boulangère";
	private IAbonne abMoins16Ans;
	private IAbonne abMajeur;

	@BeforeEach
	public void init() {
		IVerificationAge va = new VerificationAge();
		this.dvdToutAge = new DVD(1, TITRE_TOUT_AGE, false, va);
		this.dvdPlus16Ans = new DVD(2, TITRE_PLUS_16ANS, true, va);
		this.abMoins16Ans = new Abonne(1, "ant", "Enf", LocalDate.of(2018, 5, 5));
		this.abMajeur = new Abonne(2, "eur", "Maj", LocalDate.of(1995, 5, 5));
	}

	@Test
	public void testGetter() {
		assertEquals(DVDTest.TITRE_TOUT_AGE, this.dvdToutAge.getTitre());
		assertEquals(DVDTest.TITRE_PLUS_16ANS, this.dvdPlus16Ans.getTitre());

		assertFalse(this.dvdToutAge.estPourAdulte());
		assertTrue(this.dvdPlus16Ans.estPourAdulte());

		assertTrue(this.dvdToutAge.estDisponible());
		assertTrue(this.dvdPlus16Ans.estDisponible());

		assertNotEquals(this.dvdToutAge.numero(), this.dvdPlus16Ans.numero());
	}

	@Test
	public void testEmpruntDVDGeneral() {
		assertNull(this.dvdToutAge.empruntePar());
		assertDoesNotThrow(() -> this.dvdToutAge.emprunt(abMajeur));
		assertEquals(this.abMajeur, this.dvdToutAge.empruntePar());
		assertTrue(this.dvdToutAge.estEmprunte());
		assertThrows(LivreIndisponibleException.class, () -> this.dvdToutAge.emprunt(abMajeur));
		assertDoesNotThrow(() -> this.dvdToutAge.retour());
		assertNull(this.dvdPlus16Ans.empruntePar());
		assertTrue(this.dvdToutAge.estDisponible());
		assertDoesNotThrow(() -> this.dvdToutAge.emprunt(abMajeur));
	}

	@Test
	public void testEmpruntDVDToutPublic() {
		assertDoesNotThrow(() -> this.dvdToutAge.emprunt(abMoins16Ans));
		this.dvdToutAge.retour();
		assertDoesNotThrow(() -> this.dvdToutAge.emprunt(abMajeur));
	}

	@Test
	public void testEmpruntDVDAdulte() {
		assertThrows(LivreIndisponibleException.class, () -> this.dvdPlus16Ans.emprunt(abMoins16Ans));
		assertDoesNotThrow(() -> this.dvdPlus16Ans.emprunt(abMajeur));
	}

	@Test
	public void testReservationDVDGeneral() {
		assertNull(this.dvdToutAge.reservePar());
		assertDoesNotThrow(() -> this.dvdToutAge.reservation(abMajeur));
		assertTrue(this.dvdToutAge.estReserve());
		assertEquals(this.abMajeur, this.dvdToutAge.reservePar());
		assertThrows(LivreIndisponibleException.class, () -> this.dvdToutAge.reservation(abMoins16Ans));
		assertThrows(LivreIndisponibleException.class, () -> this.dvdToutAge.emprunt(abMoins16Ans));
		assertDoesNotThrow(() -> this.dvdToutAge.emprunt(abMajeur));
		this.dvdToutAge.retour();
		assertDoesNotThrow(() -> this.dvdToutAge.emprunt(abMoins16Ans));
	}

	@Test
	public void testReservationDVDToutPublic() {
		assertDoesNotThrow(() -> this.dvdToutAge.reservation(abMoins16Ans));
		this.dvdToutAge.retour();
		assertDoesNotThrow(() -> this.dvdToutAge.emprunt(abMajeur));
	}

	@Test
	public void testReservationDVDAdulte() {
		assertThrows(LivreIndisponibleException.class, () -> this.dvdPlus16Ans.emprunt(abMoins16Ans));
		assertNull(this.dvdPlus16Ans.empruntePar());
		assertDoesNotThrow(() -> this.dvdPlus16Ans.emprunt(abMajeur));
	}
}
