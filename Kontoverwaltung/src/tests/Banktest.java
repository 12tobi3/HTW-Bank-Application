package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import bankverwaltung.Anrede;
import bankverwaltung.Bank;
import bankverwaltung.Konto;
import bankverwaltung.Kunde;

class Banktest {
	
	@Test
	 public void summeAllerKontostände() {
		Bank bank = new Bank("Test", "123", "123");
		double gesamtSaldo;
		//Erstelle 3 Privat- und 3 Geschäftskunden
		for (int i = 0; i<3;i++) {
			bank.erstellePrivatkunde(Anrede.HERR, "Peter","Lustig");
			bank.erstelleGeschaeftskunde("Test");
		}
		

		//Erstelle für jeden Kunden ein Konto mit dem Kontostand 100€
		for (Kunde kunde : bank.getKunden()) {
			bank.eroeffneKonto(kunde);
			kunde.getKonto(0).setKontostand(100);;
			
		}
		gesamtSaldo = bank.gesamtSaldo();
		double expected = 600;
		assertEquals(expected, gesamtSaldo);
	}

	@Test
	public void testeKontoLimit() {
		Bank bank = new Bank("Test", "123", "123");
		bank.erstelleGeschaeftskunde("test");
		
		//Erstelle Kunde mit 10 Konten
		for (Kunde kunde : bank.getKunden()) {
			for(int i = 0; i< 10; i++) {
				kunde.addKonto(new Konto(bank.generateIBAN(kunde)));				
			}
		}
		Assertions.assertThrows(IndexOutOfBoundsException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				bank.getKunde(0).addKonto(new Konto(bank.generateIBAN(bank.getKunde(0))));
				
			}
		});
		
	}
	
	@Test
	public void testeEinzahlen() {
		Bank bank = new Bank("Test", "123", "123");
		bank.erstelleGeschaeftskunde("test");
		bank.eroeffneKonto(bank.getKunde(0));
		
		//100€ Einzahlen
		bank.getKunde(0).einzahlen(100, bank.getKunde(0).getKonto(0));;
		assertTrue(bank.getKunde(0).getKonto(0).getKontostand()==100);
	}
	
	@Test
	public void testeAuszahlen() {
		Bank bank = new Bank("Test", "123", "123");
		bank.erstelleGeschaeftskunde("test");
		bank.eroeffneKonto(bank.getKunde(0));
		bank.getKunde(0).getKonto(0).setKontostand(100);
		
		//100€ Auszahlen
		bank.getKunde(0).abheben(100, bank.getKunde(0).getKonto(0));;
		assertTrue(bank.getKunde(0).getKonto(0).getKontostand()==0);
		
	}
	
}
