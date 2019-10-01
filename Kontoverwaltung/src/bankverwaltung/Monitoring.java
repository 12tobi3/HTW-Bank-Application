package bankverwaltung;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Monitoring implements Runnable{

	Bank bank;

	private static PrintWriter out;
	private static String getTime() {
		return LocalDateTime.now().toString();
	}

	public Monitoring(Bank bank) {
		this.bank = bank; 
	}
	
	boolean isRunning = false; 
	@Override
	public void run() {
		isRunning = true; 
		
		while(isRunning){
			try {	
		
				if (out == null) {
					FileOutputStream fos;
				try {
					fos = new FileOutputStream(new File("monitoring.txt"), true);
					out = new PrintWriter(fos);
					out.append(getTime() + ": Monitoring gestartet\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			out.append(getTime() + "Die Bank verwaltet einen Gesamtkontostand in Höhe von: " + bank.gesamtSaldo()+ " Euro\n");
			out.flush();
			
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				isRunning = false;
				e.printStackTrace();
			}
		}
		}

	public void stop() {
		isRunning = false;
	}
	public boolean isRunning() {
		return isRunning;
	}

}
