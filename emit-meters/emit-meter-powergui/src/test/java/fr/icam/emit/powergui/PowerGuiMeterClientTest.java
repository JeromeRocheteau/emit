package fr.icam.emit.powergui;

import java.io.OutputStream;
import java.net.URI;
import java.util.Calendar;

import fr.icam.emit.api.Meter;

public class PowerGuiMeterClientTest {
	
	private final static String HOST = "http://172.16.220.250:8088/powerguimeter/";

	private static final int PROC_TIME = 5000; 
	
    public static void main(String[] args) throws Exception {
    	String result;
    	URI uri = URI.create(HOST);
    	Meter app = new Meter(uri);
    	long time = Calendar.getInstance().getTimeInMillis();
    	long stop = time;
    	app.doStart();
    	while ((time - stop) < PROC_TIME) {
    		time = Calendar.getInstance().getTimeInMillis();
    	}
    	app.doStop();
    	    	
    	OutputStream out = System.out;
    	result =app.doRetrieve(out);
    	System.out.println("information");
    	System.out.println(result);
    }

}