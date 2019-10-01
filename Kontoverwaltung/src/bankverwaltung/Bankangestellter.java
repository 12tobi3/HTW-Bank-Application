package bankverwaltung;

import java.util.Observable;
import java.util.Observer;

public class Bankangestellter implements Observer{
	String vorname, nachname;
	
	public Bankangestellter(String vorname, String nachname) {
		this.vorname = vorname; 
		this.nachname = nachname;
	}
	
	public void update(Observable o, Object arg) {
		String iban = arg.toString().substring(0, 24);
		String betrag = arg.toString().substring(24);
		System.out.println("Bankangstellter "+ vorname+" "+nachname + " hat die Information erhalten, dass eine Einzahlung/Auszahlung in Höhe von "+ betrag+ "€ auf das Konto " + iban+" stattgefunden hat");
	}
	

	
}
