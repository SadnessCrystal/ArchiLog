package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import abonne.Abonne;
import abonne.IAbonne;
import dvd.DVD;
import dvd.IDVD;
import dvd.IVerificationAge;
import dvd.VerificationAge;

public class VerificationAgeTest {
	private IVerificationAge va;
	private IDVD dvd1;
	private IDVD dvd2;
	private IAbonne ab1;
	private IAbonne ab2;
	
	@BeforeEach
	public void init() {
		this.va = new VerificationAge();
		this.dvd1 = new DVD(1, "Top 10 des meilleurs cours de Java", false, this.va);
		this.dvd2 = new DVD(2, "9 999 heures pour rendre ses tests unitaires sexy", true, this.va);
		this.ab1 = new Abonne(1, "-Oui", "Oui", LocalDate.of(2020, 11, 14));
		this.ab2 = new Abonne(2, "La best", "Crystal", LocalDate.of(2003, 01, 01));
	}
	
	@Test
	public void testVerificationPlus16AnsPositif() {
		assertTrue(this.va.verificationAdulte(dvd1, ab1));
		assertTrue(this.va.verificationAdulte(dvd1, ab2));
		assertTrue(this.va.verificationAdulte(dvd2, ab2));
	}
	
	@Test
	public void testVerificationPlus16AnsNegatif() {
		assertFalse(this.va.verificationAdulte(dvd2, ab1));
	}
}
