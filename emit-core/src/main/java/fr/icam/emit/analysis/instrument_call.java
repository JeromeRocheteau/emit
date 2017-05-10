package fr.icam.emit.analysis;

import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.icam.emit.api.Meter;

public class instrument_call {
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
    	
    	List<List<String>> lines = new ArrayList<List<String>>();
    	InstrumentReader reader = new InstrumentReader();
    	lines = reader.Read(result);
    	//reader.Afficher();
    	
    }
}
