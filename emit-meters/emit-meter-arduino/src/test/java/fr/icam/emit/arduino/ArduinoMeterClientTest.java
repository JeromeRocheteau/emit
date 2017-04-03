package fr.icam.emit.arduino;

import java.util.List;
import java.util.Calendar;

import fr.icam.emit.api.Feature;
import fr.icam.emit.api.ObserverInstrument;
import fr.icam.emit.entities.Instrument;

public class ArduinoMeterClientTest {
	
	private final static String HOST = "http://localhost:8080/arduinometer/";

	private static final int PROC_TIME = 3000; 
	
    public static void main(String[] args) throws Exception {
    	Instrument instrument = new Instrument();
    	instrument.setIdentifier(HOST);
    	ObserverInstrument app = new ObserverInstrument(instrument);
    	long time = Calendar.getInstance().getTimeInMillis();
    	long stop = time;
		System.out.println(app.isObserver());
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