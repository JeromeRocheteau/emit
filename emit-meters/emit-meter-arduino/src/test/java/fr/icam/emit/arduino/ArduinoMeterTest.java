package fr.icam.emit.arduino;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;

import fr.icam.emit.arduino.ArduinoMeter;

public class ArduinoMeterTest {

	private static final int PROC_TIME = 3000; 
	
    public static void main(String[] args) throws Exception {
    	ObjectMapper map = new ObjectMapper();
    	ArduinoMeter app = new ArduinoMeter();
    	app.setUp();
    	long time = Calendar.getInstance().getTimeInMillis();
    	long stop = time;
    	app.doStart();
    	while ((time - stop) < PROC_TIME) {
    		time = Calendar.getInstance().getTimeInMillis();
    	}
    	app.doStop();
    	ByteArrayOutputStream stream = new ByteArrayOutputStream();
    	app.doRetrieve(stream);
    	app.tearDown();
		JavaType longType = map.getTypeFactory().constructFromCanonical(Long.class.getCanonicalName());
		CollectionType listType = map.getTypeFactory().constructCollectionType(List.class, Double.class);
		MapType mapType = map.getTypeFactory().constructMapType(TreeMap.class, longType, listType);
    	Map<Long, List<Double>> list = map.readValue(stream.toByteArray(), mapType);
    	stream.close();
    	System.out.println(list.size());
    	for (Long t : list.keySet()) {
    		System.out.print(t);
    		for (Double v : list.get(t)) {
    			System.out.print('\t');
    			System.out.print(v);
    		}
    		System.out.println();
    	}
    }

}