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
		System.out.println(app.isMeter());
		List<Feature> features = app.getFeatures();
		for (Feature f : features) {
			System.out.println(f.getName() + " -> " + f.getMeasure().getName());
		}
    	app.doStart();
    	while ((time - stop) < PROC_TIME) {
    		time = Calendar.getInstance().getTimeInMillis();
    	}
    	app.doStop();
    	app.doRetrieve(System.out);
    }

}