package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import abonne.Abonne;
import abonne.IAbonne;
import banissement.Banissement;
import banissement.IBanissement;
import dvd.IDVD;

class BanissementTest {

	IBanissement b;
	IAbonne a;
	IDVD dvd;
	Clock tempsReel;
	Clock tempsAltere;
	
	@BeforeEach
	void setUp() throws Exception {
		this.b = new Banissement();
		//this.a = new Abonne("Kevin", LocalDate.)
	}

	@Test
	void testVerificationRetard() {
		fail("Non implémenté");
	}

}
