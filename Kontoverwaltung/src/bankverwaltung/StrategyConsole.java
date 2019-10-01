package bankverwaltung;

import java.time.LocalDateTime;

public class StrategyConsole implements LogStrategys {

	private static String getTime() {
		return LocalDateTime.now().toString();
	}
	@Override
	public void export(String iban, Double betrag, String art) {
		System.out.println(getTime()+": ART: " + art + " IBAN: " + iban + " BETRAG: " + betrag+"\n");
		
	}
}


