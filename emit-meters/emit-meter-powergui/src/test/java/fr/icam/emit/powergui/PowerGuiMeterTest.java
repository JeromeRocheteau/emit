package fr.icam.emit.powergui;

import java.util.Calendar;

public class PowerGuiMeterTest {

	private static final int PROC_TIME = 3000; 
	
    public static void main(String[] args) throws Exception {
    	PowerGuiMeter app = new PowerGuiMeter();
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