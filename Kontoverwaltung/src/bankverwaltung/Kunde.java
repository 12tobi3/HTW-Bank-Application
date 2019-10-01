package bankverwaltung;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstrake Klasse, dient als Schablone für Subklassen. 
 * @author Tobias Schultze, s0544645 - E-Mail: s0544645@htw-berlin.de
 *
 */

public abstract class Kunde implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String telefonnummer, email; 
	private int anzahlKonten = 0;
	protected int kundennummer;
	private static final int MAX_ANZAHL_KONTEN = 10;

	private ArrayList<Konto> konten =new ArrayList<>();
	private Adresse adresseKunde; 
	
	/**
	 * Methode, welche die Kundennummer als Integer zurückgibt
	 * @return Gibt die Kundennumer des Kunden zurück
	 */
	public int getKundennummer() {
		return getKundennummer();
	}


	/**
	 * Abstrakte Methode, welche den Namen des Kunden als String zurückliefert
	 * @return Übergibt den Nachnamen des Kunden
	 */
	public abstract String getName();
	
	/**
	 * Methode die den jeweiligen Kundentyp zurückgibt
	 * @return ENUM Kundentyp 
	 */
	public abstract Kundentyp getKundentyp();

	
	/**
	 * Methode, welche die Anzahl der Konten als Integer zurückliefert.
	 * @return Anzahl der Konten
	 */
	public int getAnzahlKonten() {
		return anzahlKonten;
	}
	
	/**
	 * Methode, welche ein Array vom Typ Konto zurückliefert.
	 * @return Array vom Typ Konto  
	 */
	public ArrayList<Konto> getKonten(){
		return konten;
	}
	
	
	public Konto getKonto(int index) {
		try {
			return konten.get(index);
		} catch (ArrayIndexOutOfBoundsException e ) {
			System.out.println("Das Konto existiert nicht!");
			return null; 
		}
	}



	/**
	 * Methode zum Setzen der Kunden Adresse
	 * @param adresse Adresse des Kunden
	 */
	public void setAdresseKunde(Adresse adresse) {
		adresseKunde = adresse;
	}

	
	
	public static int getMaxAnzahlKonten() {
		return MAX_ANZAHL_KONTEN;
	}


	/**
	 * Methode zum Geld einzahlen auf ein bestimmtes Konto. Es wird ueberprueft, ob der Betrag, welcher eingezhalt werden soll prositiv ist.
	 * @param betrag Der Betrag welcher eingezahlt werden soll
	 * @param konto Das Konto auf das Geld eingezahlt werden soll 
	 */
	public void einzahlen(double betrag, Konto konto) {
		if (betrag > 0) {
			konto.setKontostand(betrag);
		} else {
			System.err.println("Der Betrag muss größer Null sein.");
		}
	}
	
	/**
	 * Methode zum Geld abheben von einem bestimmten Konto. Es wird ueberprueft, ob der Betrag, welcher eingezhalt werden soll prositiv ist.
	 * @param betrag Der Betrag welcher abgehoben werden soll
	 * @param konto Das Konto von dem abgehoben werden soll
	 */
	public void abheben(double betrag, Konto konto) {
		if (betrag > 0) {
			konto.setKontostand(konto.getKontostand() - betrag);
		} else {
			System.err.println("Der Betrag muss größer Null sein.");
		}
	}

	/**
	 * Methode, die überprüft ob der Kunde die maximale Kontoanzahl erreicht hat. Fall der Kunde noch freie Slots hat wird true zurüchgeliefert, ansonsten false. 
	 * @return true/false
	 */
	protected boolean kontenLimitErreicht() {
		boolean freieSlots = false; 
		if (anzahlKonten >= MAX_ANZAHL_KONTEN) {
			freieSlots = true; 
		}
		return freieSlots;
	}
	
	
	/**
	 * Methode die die Daten eines Kunden auf der Konsole ausgibt. 
	 * @param kunde Kunde,welcher ausgegeben werden soll. 
	 */
	protected void schreibeKunde() {
		System.out.printf("%15s %-20s %-20s %3d %15s %15s %20s %d %15s","Kunde: ", getName(),"Kundennummer: ", kundennummer,"Kundentyp:",getKundentyp(),"Anzahl Konten: ", anzahlKonten, "Konten: ");
		for(int i = 0; i<getAnzahlKonten();i++) {
			System.out.print(konten.get(i).getIban()+"\t");	
		}
		System.out.println();
	}
	
	public void hatNeuesKontoErstellt() {
		anzahlKonten++;
	}
	
	public double berechneGesamtSaldo() {
		double saldo = 0;
		for (Konto konto : konten) {
			saldo = saldo + konto.getKontostand();
		}
		return saldo;
	}

	public void addKonto(Konto konto) {
		if(anzahlKonten < MAX_ANZAHL_KONTEN) {
			konten.add(konto);
			anzahlKonten++;
		}else throw new IndexOutOfBoundsException("Maximale Anzahl der Konten erreicht!");
	}
}
