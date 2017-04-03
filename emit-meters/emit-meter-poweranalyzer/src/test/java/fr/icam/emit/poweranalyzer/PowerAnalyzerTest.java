package fr.icam.emit.poweranalyzer;

import java.util.Calendar;

import fr.icam.emit.poweranalyzer.PowerAnalyzer;

public class PowerAnalyzerTest {

	private static final int PROC_TIME = 3000; 
	
    public static void main(String[] args) throws Exception {
    	PowerAnalyzer app = new PowerAnalyzer();
    	app.setUp();
    	long time = Calendar.getInstance().getTimeInMillis();
    	long stop = time;
    	app.doStart();
    	while ((time - stop) < PROC_TIME) {
    		time = Calendar.getInstance().getTimeInMillis();
    	}
    	app.doStop();
    	app.doRetrieve(System.out);
    	app.tearDown();
    }

}