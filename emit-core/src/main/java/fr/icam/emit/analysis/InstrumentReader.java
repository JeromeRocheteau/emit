package fr.icam.emit.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InstrumentReader {
	
	public List<List<String>> Read(String input) {

		List<List<String>> lines = new ArrayList<List<String>>();
		Scanner inputStream;
	    inputStream = new Scanner(input);
	    
		while(inputStream.hasNext()){
			
            String line= inputStream.next();
            String[] values = line.split(" ");
            lines.add(Arrays.asList(values));
        }
		
		return lines;
	}
	
        
}