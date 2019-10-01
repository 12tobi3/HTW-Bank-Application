package bankverwaltung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Scanner;

import view.Hauptfenster;

/**
 * Klasse, welches das Menu der Bankverwaltung ausgibt und die gewünschten Aktionen umsetzt.
 * @author Tobias Schultze, s0544645 - E-Mail: s0544645@htw-berlin.de
 *
 */
public class Menu {
	Scanner eingabe = new Scanner(System.in);
	int auswahl;
	Bank bank;
	Anrede anrede;
	Kundentyp kundentyp;
	Boolean exit = false; 
	Monitoring monitor = null; 
	
	/**
	 * Konstruktor zum erstellen eines Bankverwaltungs-Menus 
	 * @param bank Die jeweilige Bank, die das Menu aufrufen will. 
	 */
	public Menu(Bank bank) {
		this.bank = bank;
	}

	/**
	 * Methode zum starten des Menus. Das Menu wird ausgegeben und wird solange ausgeführt bis der Benutzer "0" eingibt.
	 */
	public void starteMenu() {
		schreibeHeader();
		while (!exit) {
			schreibeMenu();
			int auswahl = getAuswahl();
			performAction(auswahl);
		}
	}

	/**
	 * Methode zum Ausgeben des Menu-Headers
	 */
	private void schreibeHeader() {
		System.out.println("*+------------------------------------------------------------------+*");
		System.out.println("*|               Willkommen bei der Bankverwaltung der              |*");
		System.out.printf("%s %40s %26s%n","*|", bank.getName(),"|*");
		System.out.println("*+------------------------------------------------------------------+*");
		System.out.println("");
	}

	/**
	 * Methode, welche je nach Eingabe des Benutzer eine Aktion ausführt.
	 * @param auswahl Auswahl die der Benutzer getroffen hat. 
	 * Auswahlmöglichkeiten: 
	 * 1) Privatkunden anlegen
	 * 2) Geschäftskundenanlegen
	 * 3) Konto einem Kunden hinzufügen
	 * 4) Kunden suchen (Auswahl durch Kundennummer)
	 * 5) Kunde suchen (Auswahl durch Name)
	 * 6) Konto anzeigen (Auswahl durch IBAN)
	 * 0) EXIT (beendet die Anwendung)
	 */
	private void performAction(int auswahl) {
		boolean valid = false;
		switch (auswahl) {
			case 1:{
				String vorname = "";
				String nachname = "";
				valid = false;
				anrede = uberpruefeAnredeEingabe();
			
				while (!valid) {
					System.out.println("Bitte geben Sie den Vornamen ein: ");
					try {
						vorname = eingabe.nextLine();
						valid = ueberpruefeTextStringBuchstaben(vorname);	
					} catch (Exception e) {
						System.err.println("UNGÜLTIGER WERT!");
					}	
				}
				valid = false;
				while (!valid) {					
					System.out.println("Bitte geben Sie den Nachnamen ein: ");
					try {
						nachname = eingabe.nextLine();
						valid = ueberpruefeTextStringBuchstaben(nachname);
					} catch (Exception e) {
						System.out.println("UNGÜLTIGER WERT!");
					}			
				}
				bank.erstellePrivatkunde(anrede, vorname.trim().substring(0,1).toUpperCase() + vorname.trim().substring(1).toLowerCase(), nachname.trim().substring(0,1).toUpperCase() + nachname.trim().substring(1).toLowerCase());
				System.out.println("Sie haben den "+ bank.getKunde(bank.getAnzahlKunden()-1).getKundentyp()+"N " + anrede + " " + bank.getKunde(bank.getAnzahlKunden()-1).getName()+" erstellt!");
				System.out.println("");	
				break;
			}
			case 2:{
				String name = "";
				valid = false;
				while (!valid) {
					System.out.println("Bitte geben Sie den Namen ein: ");
					try {
						name = eingabe.nextLine();						
						
					} catch (StringIndexOutOfBoundsException e){
						System.err.println("UNGÜLTIGER WERT!");
					}
					if (!name.isEmpty()) {
						valid = true;
					}
				}	
					bank.erstelleGeschaeftskunde(name.trim());
					System.out.println("Sie haben den "+ bank.getKunde(bank.getAnzahlKunden()-1).getKundentyp()+"N " + bank.getKunde(bank.getAnzahlKunden()-1).getName() +" erstellt!");
					System.out.println("");
				
				break;
			}
			case 3:{
				int kundennummer = 0; 
				System.out.println("Bitte geben Sie die Kundennummer ein");
				try {
					kundennummer = Integer.parseInt(eingabe.nextLine());
				} catch (NumberFormatException e){
					System.out.println("Bitte geben Sie eine gültige Nummer ein!");
				}
				if (bank.sucheKunde(kundennummer) != null) {
					Kunde kunde = bank.getKunde(kundennummer-1);
					if(!kunde.kontenLimitErreicht()) {
						bank.eroeffneKonto(kunde);
						System.out.println("Sie haben für den "+ kunde.getKundentyp()+ "N "+ kunde.getName()+" das Konto: "+ kunde.getKonto(kunde.getAnzahlKonten()-1).getIban()+ " erstellt!");
					} else {
						System.out.println("Der Kunde hat bereits die maximale Anzahl an Konten!");
					}
					
					
				} else						
					System.out.println("Die Kundennummer: "+kundennummer+ " existiert nicht!");
				break;
			}
			case 4:{
				int kundennummer = 0;
				valid = false;
				while (!valid) {
					System.out.println("Bitte geben Sie die Kundennummer ein oder 0, um zum Menu zurückzukehren: ");
					try {
						kundennummer = Integer.parseInt(eingabe.nextLine());						
					}catch(NumberFormatException e ) {
						System.out.println("Bitte geben Sie ausschließlich Zahlen ein!");
					}
					if (bank.sucheKunde(kundennummer) != null) {
						bank.getKunde(kundennummer-1).schreibeKunde();
						valid = true;												
					} else if(kundennummer == 0) {
						valid = true;
					}
					else {
						System.out.println("Die eingebene Kundennummer" +kundennummer+ " existiert nicht!");
					}
		
				}
				break;
			}			
			case 5:{
				valid = false; 
				String name ="";
				while(!valid) {
					System.out.println("Bitte geben Sie den Namen des Kunden ein oder 0, um zum Menu zurückzukehren: ");
					try {
						name = eingabe.nextLine();
					} catch (Exception e) {
						System.out.println("UNGÜLTIGE EINGABE!");
					}
					if (bank.sucheKunde(name) != null) {
						bank.sucheKunde(name).schreibeKunde();
						valid = true;
					} else if(name.equals("0")) {
						valid=true; 
					}
					else {
						System.out.println("Der Kunde mit dem Namen: "+ name + " existiert nicht!");
					}	
				}
				break;
			}
			case 6:{
				valid = false;
				boolean kontoGefunden = false; 
				Map<String, Konto> konten = bank.getKontenListe();				
				String iban = "";
													
				System.out.println("Bitte geben Sie die IBAN des Kontos ein: ");
				iban = eingabe.nextLine();
				
				for (Map.Entry<String, Konto> entry: konten.entrySet() ) {
					if (entry.getKey().equals(iban)) {
						Konto konto = entry.getValue();
						kontoGefunden = true;
						System.out.println("Konto: " + konto.getIban()+ "   Kontostand: " +konto.getKontostand());
					}
				}
				if (!kontoGefunden) {
					System.out.println("Die eingegebene IBAN existiert nicht!");
					System.out.println("");
				}
				break;	
			}
			case 7: {
				bank.schreibeAlleKundenSortiertNachKundennummer();
				break;
			}case 8: {
				bank.schreibeAlleKundenSortiertNachNamen();
				System.out.println();
				System.out.println();
				break;
			}
			case 9: {
				bank.listeKonten();
				break;
			}
			case 10: {
				String path = "", dateiname = "";
				valid = false;
				while(!valid) {
					System.out.println("Bitte geben Sie den Dateipfad an!");
					path = eingabe.nextLine();	
					valid = ueberpruefreDateipfad(path);
				}
				valid = false;
				while (!valid) {					
					System.out.println("Bitte geben Sie den Namen der Datei ein!");
					dateiname = eingabe.nextLine();
					valid = ueberpruefeTextStringBuchstabenUndZahlen(dateiname);
				}
				speicherBank(path,dateiname);
				break;
			}
			case 11: {
				String path = "",dateiname ="";
				valid = false;
				while(!valid) {
					System.out.println("Bitte geben Sie den Dateipfad an!");
					path = eingabe.nextLine();	
					valid = ueberpruefreDateipfad(path);
				}
				valid = false;
				while (!valid) {					
					System.out.println("Bitte geben Sie den Namen der Datei ein!");
					dateiname = eingabe.nextLine();
					valid = ueberpruefeTextStringBuchstabenUndZahlen(dateiname);
				}
				path = path+dateiname;
				ladeBank(path);
				break;
			}
			case 12: {
				String path = "",dateiname ="";
				valid = false;
				while(!valid) {
					System.out.println("Bitte geben Sie den Dateipfad an!");
					path = eingabe.nextLine();	
					valid = ueberpruefreDateipfad(path);
				}
				valid = false;
				while (!valid) {					
					System.out.println("Bitte geben Sie den Namen der Datei ein!");
					dateiname = eingabe.nextLine();
					valid = ueberpruefeTextStringBuchstabenUndZahlen(dateiname);
				}
				exportiereBank(path, dateiname);
				break;
			}
			case 13: {
				//Geld einzahlen
				String tmpIBAN = "";
				System.out.println("Bitte geben Sie die IBAN ein");
				tmpIBAN = eingabe.nextLine(); 
				
				if (bank.existiertKonto(tmpIBAN)) {
					double betrag = 0;
					Konto konto = bank.sucheKonto(tmpIBAN);
					System.out.println("Bitte geben Sie den Betrag ein!");
					try {
						betrag = eingabe.nextDouble();						
					} catch (Exception e) {
						System.out.println("Ungültiges Format");
					}
					konto.setKontostand(konto.getKontostand()+betrag);
					Logger log = Logger.getInstance();
					log.log(tmpIBAN, betrag, "Einzahlung");
					if(betrag>=10000) {
						String info = tmpIBAN +betrag;
						bank.addBenachrichtigung(info);
					}
				} else {
					System.out.println("Das Konto existiert nicht!");
				}
				break;
			}
			case 14: {
				// Geld auszahlen
				String tmpIBAN = "";
				System.out.println("Bitte geben Sie die IBAN ein");
				tmpIBAN = eingabe.nextLine(); 
				
				if (bank.existiertKonto(tmpIBAN)) {
					double betrag;
					Konto konto = bank.sucheKonto(tmpIBAN);
					double kontostand = konto.getKontostand();
					System.out.println("Bitte geben Sie den Betrag ein!");
					betrag = eingabe.nextDouble();
					if(kontostand>betrag) {
						konto.setKontostand(kontostand-betrag);	
						Logger log = Logger.getInstance();
						log.log(tmpIBAN, betrag, "Auszahlung");
						if(betrag>=10000) {
							String info = tmpIBAN +betrag;
							bank.addBenachrichtigung(info);
						}
					}
					else 
						System.out.println("Sie haben nicht genug Geld auf dem Konto!");
				} else {
					System.out.println("Das Konto existiert nicht!");
				}
				break;
			}
			case 15: {
				// Log-Strategie wählen
				valid = false;
				while(!valid) {
					showLogStrategien();
					int auswahl1 = getAuswahl();	
					if (auswahl1 == 1) {
						Logger logger = Logger.getInstance();
						logger.setStrategy(new StrategyConsole());
						valid = true; 
					} else if (auswahl == 2) {
						Logger logger = Logger.getInstance();
						logger.setStrategy(new StrategyFile());
						valid = true;
					}
				}
				break;
			}
			case 16: {
				//GUI für Ein- und Auszhalung
				Hauptfenster hauptfenster = new Hauptfenster(bank);
				
				break; 				
			}
			case 17: {
				monitor = new Monitoring(bank);
				Thread thread = new Thread(monitor);
				thread.start();
					}
				break;
			case 18: {
				if (monitor != null) {
					if (monitor.isRunning) {
						monitor.stop();
					}
					else {
						monitor.run();
					}
				}
				break;
			}
			case 0:{
				System.out.println("Danke für die Benutzung der Bankverwaltung!");
				System.exit(0);
				break;
			}
			default: {
				System.err.println("Unbekannter FEHLER!");
				break;
			}
		}
	}
	
	/**
	 * Methode die einen IBAN-String überprüft und je nach Richtigkeit des Formats true oder false zurückgibt
	 * @param iban Die IBAN welche überprüft werden soll
	 * @return true/false je nach Format 
	 */
	public boolean ueberpruefeIbanString(String iban){
		boolean valid = false; 
		iban = iban.replaceAll(" ", "");
		if (iban.matches("DE[0-9]*") && !iban.isEmpty()) {
			valid = true;
		}
		return valid; 
	}

	/**
	 * Methode zum Überprüfen eines Strings. Wenn dieser nur aus Buchstaben besteht, gibt die Methode true zurück, wenn nicht false. 
	 * @param string Der String der überprüft werden soll 
	 * @return true oder false 
	 */
	private boolean ueberpruefeTextStringBuchstaben(String string) {
		boolean valid = false;
		string = string.replaceAll(" ", "");
		if(string.trim().matches("[a-zA-ZöüüÖÄÜß]*") && !string.isEmpty()){
			valid = true; 
		} else {
			valid = false;
		}
		return valid;
	}
	
	private boolean ueberpruefeTextStringBuchstabenUndZahlen(String string) {
		boolean valid = false;
		string = string.replaceAll(" ", "");
		if(string.matches("[a-zA-ZöüüÖÄÜß]*") || string.trim().matches("[0-9]*") && !string.isEmpty()){
			valid = true; 
		} else {
			valid = false;
		}
		return valid;
	}
	private boolean ueberpruefreDateipfad(String string) {
		boolean valid = false;
		string = string.replaceAll(" ", "");
		if(string.matches("(/[a-zA-ZöüüÖÄÜß]*)*/")) {
			valid = true; 
		} else {
			System.out.println("Achten Sie daraus, dass der Pfad mit einem \"/\" beginnt und endet!");
			valid = false;
		}
		return valid;
	}
	/**
	 * Methode die, die eingabe des Kunden überprüft und eine Anrede vom ENUM Anrede zurück gibt. 
	 * (1) für Herr
	 * (2) für Frau
	 * @return Anrede gibt den ENUM Wert zurück
	 */
	private Anrede uberpruefeAnredeEingabe() {
		boolean valid = false;
		int wert = 0;
			do {
				System.out.println("Bitte waehlen Sie zwischen Herr (1) und Frau(2)");
				try {
					wert = Integer.parseInt(eingabe.nextLine());
					
				} catch(IllegalArgumentException exp) {
					System.out.println("UNGUELTIGER WERT!");
				}	
				if (wert == 1) {
					valid = true;
					anrede = Anrede.HERR;
				} else if (wert == 2) {
					valid = true;
					anrede = Anrede.FRAU;
				} else {
					System.out.println("Ungueltige Zahl!");
					valid = false;
				}
			} while(!valid);
		return anrede;
	}

	/**
	 * Methode zum Auswerten des Eingabe des Kunden, wobei eine Zahl zwischen 0 und 6 erwartet wird. 
	 * @return gibt die eine Zahl zwischen 0 und 6 zurück 
	 */
	private int getAuswahl() {
		int auswahl = -1;
		do {
			try {
				auswahl = Integer.parseInt(eingabe.nextLine());
			}
			catch(NumberFormatException e) {
				System.out.println("Inkorrekte Eingabe. Nur Nummern, bitte!");
				schreibeMenu();		
			}
			if(auswahl < 0 || auswahl >18) {
				System.out.println("Nummer ist ungueltig! Bitte geben Sie eine gueltige Nummer ein!");
				schreibeMenu();
			}
		} while (auswahl < 0 || auswahl > 18);
		return auswahl;
	}
	
	public void ladeBank(String path) {
		File datei = new File(path);
		if (!datei.exists()) {
			System.out.println("Die Datei existiert nicht!");
			return;
		}
		if (!datei.canRead()) {
			System.out.println("Ungültige Datei!");
			return;
		}
		try {
			FileInputStream fis = new FileInputStream(datei);
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(fis);
			bank = (Bank) ois.readObject();
			System.out.println();
			System.out.println("Sie haben erfolgreich die Datei "+path+ " geladen!");
			System.out.println();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();	
		}
	}
	
	public void speicherBank(String path, String name) {
		File datei = new File(path);
		if(datei.isDirectory()) {
			try {
				FileOutputStream fos = new FileOutputStream(path+name);
				@SuppressWarnings("resource")
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(bank);
				System.out.println();
				System.out.println("Sie haben erfolgreich die Datei "+ name+" im Verzeichnis "+ path + " gespeichert");
				System.out.println();
			} catch(IOException e) {
				e.printStackTrace();
			}	
		} else {
			System.err.println("Verzeichnis existiert nicht!");
		}
	}
	
	public void exportiereBank(String path, String name) {
		int counter = 0;
		try {
			@SuppressWarnings("resource")
			FileWriter writer = new FileWriter(path+name+".csv");
			writer.write("Kundennummer;Kundentyp;Name;Kontostand\n");
			for (Kunde kunde : bank.getKunden()) {
				double kontostand = 0;
				for (Konto konto : kunde.getKonten()) {
					kontostand = konto.getKontostand() + kontostand;
				}
				writer.write(kunde.getKundennummer() + ";" + kunde.getKundentyp() + ";" + kunde.getName() + ";" + kontostand+"\n");
				counter++;
				writer.flush();
			}
			System.out.println("Sie haben erfolgreich "+ counter + " Datensätze exportiert!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode die das Menu auf der Konsole ausgibt.
	 */
	private void schreibeMenu() {
		System.out.println("Bitte waehlen Sie eine der folgenden Optionen:    ");
		System.out.println("( 1) Privatkunde anlegen");
		System.out.println("( 2) Geschaeftskunde anlegen");
		System.out.println("( 3) Konto anlegen und Kundennummer zuordnen");
		System.out.println("( 4) Kunde mit Konten anzeigen (Auswahl durch Kundennummer)");
		System.out.println("( 5) Kunde mit Konten anzeigen (Auswahl durch Name)");
		System.out.println("( 6) Konto anzeigen (Auswahl durch IBAN)");
		System.out.println("( 7) Alle Kunden sortiert nach aufsteigender Kundennummer anzeigen");
		System.out.println("( 8) Alle Kunden sortiert nach aufsteigendem Namen und nachrangig nach absteigender Kundennummer anzeigen");
		System.out.println("( 9) Alle Konten unsortiert anzeigen");
		System.out.println("(10) Bankdaten speichern");
		System.out.println("(11) Bankdaten laden ");
		System.out.println("(12) Kunden nach Namen sortiert als CSV-Datei exportieren");
		System.out.println("(13) Geld einzahlen");
		System.out.println("(14) Geld auszahlen");
		System.out.println("(15) Log-Strategie wählen");
		System.out.println("(16) GUI für Ein- und Auszahlungen öffnen");
		System.out.println("(17) Monitoring aktivieren");
		System.out.println("(18) Monitoring deaktivieren");
		System.out.println("( 0) EXIT");
		System.out.println("");
		System.out.println("Geben Sie die entsprechende Nummer ein: ");		
	}
	
	private void showLogStrategien() {
		System.out.println("Bitte waehlen Sie eine der folgenden Log-Strategien aus:    ");
		System.out.println("( 1) Log auf der Konsole ausgeben");
		System.out.println("( 2) Log in Datei speichern");
	}
}
