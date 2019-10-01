package bankverwaltung;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class StrategyFile implements LogStrategys {
	private static PrintWriter out;
	private static String getTime() {
		return LocalDateTime.now().toString();
	}

	public void export(String iban, Double betrag, String art) {
		if (out == null) {
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(new File("log.txt"), true);
				out = new PrintWriter(fos);
				out.append(getTime() + ": Logger gestartet\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		out.append(getTime() + ": ART: " + art + " IBAN: " + iban + " BETRAG: " + betrag+"\n");
		out.flush();
		
	}


		

}
