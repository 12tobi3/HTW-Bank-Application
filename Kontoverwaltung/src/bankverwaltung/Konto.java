package bankverwaltung;

import java.io.Serializable;

/**
 * Klasse zum Erstellen von Konto-Objekten eines Kunden
 * @author Tobias Schultze, s0544645 - E-Mail: s0544645@htw-berlin.de
 *
 */

public class Konto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double kontostand = 0; 
	private String iban;
	
	/**
	 * Konstruktor zum Erzeugen eines Konto-Objektes
	 * @param iban Identikikation eines Kontos 
	 */
	public Konto(String iban) {
		this.iban =iban; 
	}
	
	/**
	 * Methode zum setzen des Kontostandes 
	 * @param kontostand Geldbetrag auf dem Konto 
	 */
	public void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}
	
	/**
	 * Methode, welche die IBAN als String zurückliefert.
	 * @return IBAN des Kontos
	 */
	public  String getIban() {
		return iban;
	}
	
	/**
	 * Methode, welche den Kontostand zurückliefert. 
	 * @return Kontostand des Kontos
	 */
	public double getKontostand() {
		return kontostand;
	}
	
	/**
	 * Methode zum Hinzufügen eines Kontos
	 * @param kunde Kunde, welchem ein Konto hinzugefügt werden soll
	 * @param bank Bank, wo das Konto erstellt werden soll
	 */
	public void fuegeKontoHinzu(Kunde kunde, Bank bank) {
		try {
			bank.eroeffneKonto(kunde);;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Kunde hat bereits die maximale Anzahl an Konten: "+kunde.getAnzahlKonten()+" !");
		}
	}
	

}
