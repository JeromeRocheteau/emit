package fr.icam.emit.arduino;

import java.util.List;
import java.net.URI;
import java.util.Calendar;

import fr.icam.emit.api.Feature;
import fr.icam.emit.api.Meter;

public class ArduinoMeterClientTest {
	
	private final static String HOST = "http://172.16.220.250:8088/arduinometer/";

	private static final int PROC_TIME = 3000; 
	
    public static void main(String[] args) throws Exception {
    	URI uri = URI.create(HOST);
    	Meter app = new Meter(uri);
    	long time = Calendar.getInstance().getTimeInMillis();
    	long stop = time;
    	System.out.println("debut");
		System.out.println(app.isMeter());
		List<Feature> features = app.getFeatures();
		for (Feature f : features) {
			System.out.println(f.getName() + " -> " + f.getMeasure().getName());
		}
		
    	app.doStart();
    	System.out.println("lancement");
    	while ((time - stop) < PROC_TIME) {
    		time = Calendar.getInstance().getTimeInMillis();
    	}
    	System.out.println("retrieve");
    	app.doStop();
    	System.out.println("retrieve 2");
    	String reponce =app.doRetrieve(System.out);
    	System.out.println(reponce);
    	System.out.println("fin");
    }

}