package bankverwaltung;

public class Logger {

		private static Logger instance;
		private LogStrategys strategy = new StrategyFile();
		
		private Logger()
		{
		}
		
	    public static Logger getInstance()
	    {
	    	if (instance == null) {
	    		instance = new Logger();
	    	}
	    	return instance;
	    }
	    public void setStrategy(LogStrategys strategy) {
	    	this.strategy = strategy;
	    }
	    public LogStrategys getStrategy() {
	    	return strategy;
	    }
	    
	    public void log(String iban, Double betrag, String art) {
	    	strategy.export(iban, betrag, art);;
	    }
		
}
