package banken;

import java.util.ArrayList;
import java.util.Random;

import bankverwaltung.Adresse;
import bankverwaltung.Anrede;
import bankverwaltung.Bank;
import bankverwaltung.Bankangestellter;
import bankverwaltung.Konto;
import bankverwaltung.Kunde;
import bankverwaltung.Menu;
import bankverwaltung.Vorstand;
import view.Hauptfenster;

/**
 * Klasse zum Testen der Bankverwaltung. Es wird eine Bank mit fünf Kunden und jeweils 0-3 Konten erstellt. 
 * Die erstellten Objekte werden anschließend auf der Konsole ausgegeben.
 * @author Tobias Schultze, s0544645 - E-Mail: s0544645@htw-berlin.de
 *
 */

public class Main {

	/**
	 * Hauptprogramm 
	 * @param args Kommandozeilenparameter
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		
		
			//Erstelle Adressen
			Adresse b1 = new Adresse("Straße 1", "12345", "Musterhausen");
			Adresse k1 = new Adresse("Straße 2", "12345", "Musterhausen");
			Adresse k2 = new Adresse("Straße 3", "12345", "Musterhausen");
			Adresse k3 = new Adresse("Straße 4", "12345", "Musterhausen");
			Adresse k4 = new Adresse("Straße 5", "12345", "Musterhausen");
			Adresse k5 = new Adresse("Straße 6", "12345", "Musterhausen");	
			
			//Erstelle Bank Objekt
			Bank postBank = new Bank("Postbank Berlin", "10010010", "PBNKDEFF");
			postBank.setAdresseBank(b1);
			
			//Erstelle Bankmitarbeiter
			postBank.addObserver(new Bankangestellter("Reiner", "Wirsig"));
			postBank.addObserver(new Vorstand("Hugo", "Boss"));
		
			
			//Erstelle Kunden
			
			//Privatkunden
			postBank.erstellePrivatkunde(Anrede.HERR, "Heiko", "Mueller", "19.09.1999", "012345");
			postBank.erstellePrivatkunde(Anrede.HERR,"Hans", "Mueller", "19.09.1999", "012345");
			postBank.erstellePrivatkunde(Anrede.FRAU, "Berta", "Mueller", "19.09.1999", "012345");
			postBank.erstellePrivatkunde(Anrede.FRAU,"Heike", "Mueller", "19.09.1999", "012345");
			postBank.erstellePrivatkunde(Anrede.FRAU,"Anna", "Mueller", "19.09.1999", "012345");
			//Geschäftskunden
			postBank.erstelleGeschaeftskunde("SAP");
			postBank.erstelleGeschaeftskunde("IBM");
			postBank.erstelleGeschaeftskunde("TESLA");
			postBank.erstelleGeschaeftskunde("DAIMLER");
			postBank.erstelleGeschaeftskunde("APPLE");
			
			//Lege Kunden Adressen an
			Kunde kunde;
			kunde = postBank.getKunde(0);
			kunde.setAdresseKunde(k1);
			kunde = postBank.getKunde(1);
			kunde.setAdresseKunde(k2);
			kunde = postBank.getKunde(2);
			kunde.setAdresseKunde(k3);
			kunde = postBank.getKunde(3);
			kunde.setAdresseKunde(k4);
			kunde = postBank.getKunde(4);
			kunde.setAdresseKunde(k5);
			
			//Erstelle Konten						
			for (int k = 0; k <= 9; k++) {
				kunde = postBank.getKunde(k);
				for (int konten=0; konten<2;konten++) {
					int zufallszahl = (int) (Math.random() * 2);
					if (zufallszahl == 1) {
						postBank.eroeffneKonto(kunde);;
					}
				}			
			}
			
			//Ausgabe

			System.out.println("-------< Bank >-------");
			System.out.println("Name: "+postBank.getName()+"\t"+"BLZ: "+ postBank.getBlz()+"\t"+"BIC: "+ postBank.getBic()+"\t"+"Anzahl der Kunden: "+ postBank.getAnzahlKunden()+"\t"+" Anzahl der Konten: "+ postBank.getKontenListe().size());
			System.out.println("Anschrift: "+b1.getAdresszeile1()+"  "+b1.getOrt()+" "+ b1.getPlz());
			System.out.println("");
			System.out.println("-------< Kunden >-------");
			System.out.printf("| %1s| %-15s| %-19s | %-20s |%11s%n", "Kundennummer","Kundentyp","Name", "Anzahl der Konten", "Konten");
			for (int i = 0; i <= 9; i++) {	
				kunde = postBank.getKunde(i);	
				ArrayList<Konto> konten = kunde.getKonten();
				System.out.printf("| %12d| %-15s| %-20s| %20d |",kunde.getKundennummer(),kunde.getKundentyp(),kunde.getName(),kunde.getAnzahlKonten());
				for (int k = 0; k < kunde.getAnzahlKonten(); k++) {
					System.out.printf("%29s", konten.get(k).getIban());
				}
				System.out.println("");
			}
			System.out.println("");
			System.out.println("");
			System.out.println("");
			
			//Starte Bankverwaltungs Menu
			Menu menu = new Menu(postBank);
			menu.starteMenu();

			
	}
}


