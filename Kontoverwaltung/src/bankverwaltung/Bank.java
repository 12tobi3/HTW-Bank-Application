package bankverwaltung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Klasse zum Erstellen einer Bank und derer Kunden
 * @author Tobias Schultze, s0544645 - E-Mail: s0544645@htw-berlin.de
 *
 */

@SuppressWarnings("rawtypes")
public class Bank extends Observable implements Comparable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name, blz, bic, laendercode;
	private int anzahlKunden = 0;
	private int anzahlPrivatkunden = 0; 
	private int anzahlGeschaeftskunden = 0; 
	
	private ArrayList<Kunde> kunden =new ArrayList<>();
	private Map<String, Konto> kontenListe = new HashMap<String, Konto>();
	private Adresse adresseBank;
	
	
	/**
	 * Konstruktor zum Erstellen von Bank-Objekten.
	 * @param name Name der Bank
	 * @param blz Bankleitzahl
	 * @param bic Bank Identifier Code 
	 */
	public Bank(String name, String blz, String bic) {
		this.name = name;
		this.blz = blz;
		this.bic = bic;
	}

	/**
	 * Methode zum Setzen der Adresse der Bank
	 * @param adresse Adresse der Bank
	 */
	public void setAdresseBank(Adresse adresse) {
		adresseBank = adresse;
	}
	
	public Map<String, Konto> getKontenListe() {
		return kontenListe;
	}


	/**
	 * Methode, welche die Anzahl der Kunden zurückliefert 
	 * @return Anzahl der Kunden
	 */
	public int getAnzahlKunden() {
		return anzahlKunden;
	}
	
	/**
	 * Methode, welche ein Kunden-Objekt aus der ArrayList Kunde zurückliefert
	 * @param index Index der ArrayList
	 * @return Kunden Objekt
	 */
	public Kunde getKunde(int index) {
		return kunden.get(index);
	}
	
	public ArrayList<Kunde> getKunden() {
		return kunden;
	}
	
	/**
	 * Methode, welche den Namen zurückliefert.
	 * @return Gibt den Namen zurück.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Methode, welche die Bankleitzahl zurückliefert. 
	 * @return Bankleitzahl
	 */
	public String getBlz() {
		return blz;
	}
	
	/**
	 * Methode, welche die Bic zurückliefert
	 * @return Bank Identifier Code
	 */
	public String getBic() {
		return bic;
	}
	
	public void erstellePrivatkunde(Anrede anrede,String vorname, String nachname) {
		 kunden.add(new Privatkunde(anrede, vorname, nachname, anzahlKunden+1));
		 anzahlKunden++;
		 anzahlPrivatkunden++;		 
	}


	/**
	 * Methode zum Erstellen von Kunden-Objekten. Zum Erstellen wird der Konstruktor der Klasse Kunde aufgerufen.
	 * Anschließend werden weitere Daten über die set-Methoden der Klasse Kunden eingepflegt.
	 * @param anrede Anrede des Kunden
	 * @param vorname Vorname des Kunden
	 * @param nachname Nachname des Kunden
	 * @param geburtsdatum Geburtsdatum des Kunden
	 * @param telefonnummer Telefonnummer des Kunden
	 */
	public void erstellePrivatkunde(Anrede anrede,String vorname, String nachname, String geburtsdatum, String telefonnummer) {
		 kunden.add(new Privatkunde(vorname, nachname, anzahlKunden+1));
		 Privatkunde privatKunde = (Privatkunde) kunden.get(anzahlKunden);
		 privatKunde.setAnrede(anrede);
		 privatKunde.setGeburtsdatum(geburtsdatum);
		 privatKunde.setTelefonnummer(telefonnummer);
		 anzahlKunden++;
		 anzahlPrivatkunden++;
	}
	/**
	 * Methode zum Erstellen von Geschäftskunden
	 * @param firmenname Name des Kunden
	 */
	public void erstelleGeschaeftskunde(String firmenname) {
		kunden.add(new Geschaeftskunde(firmenname, anzahlKunden+1));
		anzahlKunden++;
		anzahlGeschaeftskunden++;
	}
	
	/**
	 * Methode zum Suchen eines Kunden (Namen)
	 * @param name Name nach dem gesucht werden muss
	 * @return gibt den Kunden zurück
	 */
	public Kunde sucheKunde(String name) {
		Kunde kunde = null; 
		for(int i = 0; i < anzahlKunden;i++) {
			if (kunden.get(i).getName().equalsIgnoreCase(name)) {
				kunde =  kunden.get(i);
			}		 
		}
		return kunde;
	}
	/**
	 * Methode zum Suchen eines Kunden (Kundennummer)
	 * @param kundennummer
	 * @return gibt den Kunden zurück
	 */
	public Kunde sucheKunde(int kundennummer) {
		Kunde kunde = null; 
		for(int i = 0; i < anzahlKunden;i++) {
			if (kunden.get(i).getKundennummer() == kundennummer) {
				kunde = kunden.get(i);
			}		 
		}
		return kunde;
	}
	
	public boolean existiertKonto(String iban) {
		boolean kontoGefunden = false;
		for (Map.Entry<String, Konto> entry: kontenListe.entrySet() ) {
			if (entry.getKey().equals(iban)) {
				kontoGefunden = true;
			}
		}
		return kontoGefunden;
	}
	
	public Konto sucheKonto(String iban) {
		Konto konto = null;
		for (Map.Entry<String, Konto> entry: kontenListe.entrySet() ) {
			if (entry.getKey().equals(iban)) {
				 konto = entry.getValue();
		}
	}
		return konto;
	}
	
	public void listeKonten() {
		
		/* Iterator i = kontenListe.entrySet().iterator();
		 
		
		while(i.hasNext()) {
			Konto konto = (Konto) ((Map.Entry)i.next()).getValue();
			System.out.print(((Map.Entry)i.next()).getKey());
			System.out.println("   "+konto.getKontostand());
		}
		*/
		
		for (String key : kontenListe.keySet()) {
			System.out.print("IBAN:  "+key+"\t");
			System.out.println("\tKontostand:  "+kontenListe.get(key).getKontostand());
		}
	}	
		
		/**
		 * Methode zum Eroeffnen eines Kontos. Es wird eine IBAN aus dem Ländercode, der Bankleitzahl und der Kontonummer erzeugt. 
		 * Wenn die Anzahl der Konto kleiner zehn ist, wird der Konstruktor der Klasse Konto aufgerufen, um Konto-Objekt zu erstellen, ansonsten wird eine Fehlermeldung ausgegeben. 
		 * @param blz Bankleitzahl der Bank auf der das Konto erstellt wird
		 * @exception Versucht ein neues Konto zu initialisieren und das Objekt dem Konten Array hinzuzufügen, falls die maximale Anzahl überschritten ist, wird eine Fehlermeldung ausgegeben und kein neues Objekt initialisiert.
		 */
		public void eroeffneKonto(Kunde kunde){
			if (!kunde.kontenLimitErreicht()) {
				String iban = generateIBAN(kunde);
				kunde.addKonto(new Konto(iban));
				kontenListe.put(iban, kunde.getKonto(kunde.getAnzahlKonten()-1));		
			}

		}
		
		public String generateIBAN(Kunde kunde) {
			String iban;
			int kontonummer = (kunde.getKundennummer()*Kunde.getMaxAnzahlKonten()-kunde.getAnzahlKonten());
			iban = "DE18 " + blz + " " + String.format("%010d",kontonummer );
			return iban;			
		}
		
		public void schreibeAlleKundenSortiertNachNamen() {
			
			@SuppressWarnings("unchecked")
			ArrayList<Kunde> sortierteListe = (ArrayList<Kunde>) kunden.clone();
			Collections.sort(sortierteListe, new Comparator<Kunde>() {
				public int compare(Kunde k1, Kunde k2){
					int c1 = Integer.valueOf(k1.getName().compareToIgnoreCase(k2.getName()));
					
					if (c1!=0) {
						return c1; 
					}
					int c2 = Integer.valueOf(k2.getKundennummer()).compareTo(k1.getKundennummer());
					return c2;
				}
			});
			
			for (Kunde kunde : sortierteListe) {
				kunde.schreibeKunde();
			}
			
			for(Kunde kunde1 : kunden) {
				kunde1.schreibeKunde();
			}
		}
		
		public void schreibeAlleKundenSortiertNachKundennummer() {
			@SuppressWarnings("unchecked")
			ArrayList<Kunde> sortierteListe = (ArrayList<Kunde>) kunden.clone();
			
			Collections.sort(sortierteListe, new Comparator<Kunde>() {
				public int compare(Kunde k1, Kunde k2){
					return Integer.valueOf(k1.getKundennummer()).compareTo(k2.getKundennummer());
				}
			});
			
			for (Kunde kunde : sortierteListe) {
				kunde.schreibeKunde();
			}
		}		
		
		public void addBenachrichtigung(String info) {
			setChanged();
			notifyObservers(info);
		}
		public int compareTo(Kunde kunde) {
			// TODO Auto-generated method stub
			return this.getName().compareTo(kunde.getName());
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}	
		
		public double gesamtSaldo() {
			double gesamtSaldo = 0; 
			
			for (Konto konto : kontenListe.values()) {
				gesamtSaldo += konto.getKontostand();
			}
			return gesamtSaldo;
		}
	
}
