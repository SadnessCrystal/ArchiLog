package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import abonne.Abonne;

public class AbonneTest {
	private Abonne ab;
	private String nomAb;
	private LocalDate dateNaissance;
	
	@BeforeEach
	public void init() {
		this.nomAb = "Manu Macaron";
		this.dateNaissance = LocalDate.of(1960, 12, 12);
		this.ab = new Abonne(this.nomAb, this.dateNaissance);
	}
	
	@Test
	public void gettersTests() {
		assertEquals(this.dateNaissance, this.ab.getDateNaissance());
		assertEquals(this.nomAb, ab.getNom());
		assertEquals(1, ab.getId());
		assertEquals(62, this.ab.getAge());
	}
}
