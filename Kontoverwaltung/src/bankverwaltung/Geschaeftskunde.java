package bankverwaltung;

/**
 * Klasse zum Erstellen von Geschaeftskunden 
 * @author Tobias Schultze, s0544645 - E-Mail: s0544645@htw-berlin.de
 *
 */
public class Geschaeftskunde extends Kunde{
	
	String name; 
	final static Kundentyp kundentyp = Kundentyp.GESCHÄFTSKUNDE;

	/**
	 * Get-Methode für den Namen 
	 * @return Name des Kunden 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set-Methode für den NAmen 
	 * @param name Name des Kunden
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get-methode für die Kundennummer
	 * @return Kundenummer
	 */
	public int getKundennummer() {
		return kundennummer;
	}

	/**
	 * get-methode für den Kundentyp
	 * @return Kundentyp 
	 */
	public Kundentyp getKundentyp() {
		return kundentyp;
	}

	/**
	 * Konstruktor 
	 * @param name Name des Kunden
	 * @param kundennummer Kundennummer des Kunden 
	 */
	protected Geschaeftskunde(String name, int kundennummer) {
		this.name = name;
		this.kundennummer = kundennummer;
		// TODO Auto-generated constructor stub
	}

}
