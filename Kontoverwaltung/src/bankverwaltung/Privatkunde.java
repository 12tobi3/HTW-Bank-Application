package bankverwaltung;

/**
 * Klasse zum Erstellen von Privatkunden-Objekten
 * @author Tobias Schultze, s0544645 - E-Mail: s0544645@htw-berlin.de
 *
 */
public class Privatkunde extends Kunde {
	
	private Anrede anrede; 
	private String vorname, nachname, geburtsdatum, telefonnummer; 
	



	final static Kundentyp kundentyp = Kundentyp.PRIVATKUNDE;

/**
 * Konstruktor 
 * @param anrede Anrede des Kunden (Herr/Frau)
 * @param vorname Vorname des Kunden
 * @param nachname Nachname des Kunden
 * @param kundennummer Kundennummer des Kunden
 */
	protected Privatkunde(Anrede anrede ,String vorname, String nachname, int kundennummer) {
	
		super.kundennummer = kundennummer;
		this.anrede = anrede;
		this.vorname = vorname;
		this.nachname = nachname;
		
	}
	
	/**
	 * Konstruktor  
	 * @param vorname Vorname des Kunden
	 * @param nachname Nachname des Kunden 
	 * @param kundennummer Kundennummer des Kunden
	 */
	protected Privatkunde(String vorname, String nachname, int kundennummer) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.kundennummer = kundennummer;
	}

	/**
	 * Get-Methode für die Anrede des Kunden
	 * @return Anrede des Kunden (Herr/Frau)
	 */
	public Anrede getAnrede() {
		return anrede;
	}

	/**
	 * Set-methode für die Anrede
	 * @param anrede Anrede des Kunden 
	 */
	public void setAnrede(Anrede anrede) {
		this.anrede = anrede;
	}
	
	/**
	 * Get-Methode für den Namen
	 * @return Namen des Kunden (Vor- + Nachname)
	 */
	public String getName() {
		return vorname+" "+nachname;
	}

	/**
	 * Get-Methode für den Vornamen des Kunden 
	 * @return Vorname 
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * Set-methode für den Vornamen 
	 * @param vorname Vorname des Kunden 
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * Get-Methode für den NAchnamen 
	 * @return Nachname
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * Set-Methode für den Nachnamen
	 * @param nachname Nachname
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * Get-Methode für das geburtsdatum 
	 * @return Geburtsdatum 
	 */
	public String getGeburtsdatum() {
		return geburtsdatum;
	}

	/**
	 * Set-Methode für das Geburtsdatum
	 * @param geburtsdatum Geburtsdatum 
	 */
	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	/**
	 * Get-Methode für die Kundennummer
	 * @return Kundennummer
	 */
	public int getKundennummer() {
		return kundennummer;
	}

	/**
	 * Get-Methode für den Kundentyp 
	 * @return Kundentyp 
	 */
	public Kundentyp getKundentyp() {
		return kundentyp;
	}
	/**
	 * Get-Methode für die Telefonnummer
	 * @return Telefonnummer
	 */
	public String getTelefonnummer() {
		return telefonnummer;
	}
	/**
	 * Set-Methode für die Telefonnummer
	 * @param telefonnummer Telefonnummer
	 */
	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

}
