package bankverwaltung;

import java.io.Serializable;

/**
 * Klasse zum Erstellen von Adress-Objekten für Banken und Kunden
 * @author Tobias Schultze, s0544645 - E-Mail: s0544645@htw-berlin.de
 *
 */

public class Adresse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String adresszeile1, adresszeile2, plz, ort; 
	
	/**
	 * Konstruktor eines Adress-Objektes
	 * @param adresszeile1 Straße und Hausnummer 
	 * @param plz Postleitzahl 
	 * @param ort Ort
	 */
	public Adresse(String adresszeile1, String plz, String ort) {
		this.adresszeile1 = adresszeile1;
		this.plz = plz; 
		this.ort = ort;
	}
	
	/**
	 * Methode zum setzen der Klasseneigenschaften
	 * @param adresszeile1 Straße und Hausnummer 
	 * @param adresszeile2 Etage, Stockwerk, etc. 
	 * @param plz Postleitzahl
	 * @param ort Ort
	 */
	public void setAdresse(String adresszeile1, String adresszeile2, String plz, String ort) {
		this.adresszeile1 = adresszeile1;
		this.adresszeile2 = adresszeile2;
		this.ort = ort; 
		this.plz = plz;
	}

	/**
	 * Methode die Adressezeile1 als String zurückliefert.
	 * @return Gibt die Adresszeile1 zurück
	 */
	public String getAdresszeile1() {
		return adresszeile1;
	}

	/**
	 * Methode zum setzen der Adresszeile1
	 * @param adresszeile1 Straße und Ort 
	 */
	public void setAdresszeile1(String adresszeile1) {
		this.adresszeile1 = adresszeile1;
	}

	/**
	 * Methode, welche die Adresszeile2 zurückgibt
	 * @return Gibt die Adresszeile2 zurück
	 */
	public String getAdresszeile2() {
		return adresszeile2;
	}

	/**
	 * Methode zum setzen der Adresszeile2
	 * @param adresszeile2 Etage, Stockwerk, etc
	 */
	public void setAdresszeile2(String adresszeile2) {
		this.adresszeile2 = adresszeile2;
	}

	/**
	 * Methode, welche die Postleitzahl als String zurückgibt
	 * @return Gibt die Postleitzahl zurück
	 */
	public String getPlz() {
		return plz;
	}

	/**
	 * Methode zum setzen der Postleitzahl
	 * @param plz Postleitzahl
	 */
	public void setPlz(String plz) {
		this.plz = plz;
	}

	/**
	 * Methode, welche den Ort als String zurückliefert
	 * @return Gibt den Ort zurück
	 */
	public String getOrt() {
		return ort;
	}

	/**
	 * Methode zum Setzen des Ortes
	 * @param ort Ort
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	
}	
